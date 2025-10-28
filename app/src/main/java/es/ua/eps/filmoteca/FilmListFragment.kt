package es.ua.eps.filmoteca

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class FilmListFragment : ListFragment() {

    interface OnFilmSelectedListener {
        fun onFilmSelected(film: Film)
    }

    private var listener: OnFilmSelectedListener? = null

    private val films = listOf(
        Film("The Godfather", 1972, "Francis Ford Coppola", "Mafia, familia, poder."),
        Film("Pulp Fiction", 1994, "Quentin Tarantino"),
        Film("Inception", 2010, "Christopher Nolan"),
        Film("The Matrix", 1999, "Lana & Lilly Wachowski"),
        Film("Spirited Away", 2001, "Hayao Miyazaki")
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            parentFragment is OnFilmSelectedListener -> parentFragment as OnFilmSelectedListener
            context is OnFilmSelectedListener -> context
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            films.map { it.title }
        )
        listView.choiceMode = android.widget.AbsListView.CHOICE_MODE_SINGLE
    }

    override fun onListItemClick(l: android.widget.ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val film = films[position]
        listener?.onFilmSelected(film)
        listView.setItemChecked(position, true)
    }
}

