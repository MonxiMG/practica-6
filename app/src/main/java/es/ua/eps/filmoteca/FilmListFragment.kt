package es.ua.eps.filmoteca

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class FilmListFragment : ListFragment() {

    interface OnFilmIndexSelectedListener {
        fun onFilmIndexSelected(index: Int)
    }

    private var listener: OnFilmIndexSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            parentFragment is OnFilmIndexSelectedListener -> parentFragment as OnFilmIndexSelectedListener
            context is OnFilmIndexSelectedListener -> context
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titles = FilmRepository.films.map { it.title }
        listAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            titles
        )
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        l.setItemChecked(position, true)
        listener?.onFilmIndexSelected(position)
    }
}
