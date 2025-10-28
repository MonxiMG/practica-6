package es.ua.eps.filmoteca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FilmDataFragment : Fragment() {

    companion object {
        private const val ARG_INDEX = "arg_index"
        fun newInstance(index: Int) = FilmDataFragment().apply {
            arguments = Bundle().apply { putInt(ARG_INDEX, index) }
        }
    }

    private var currentIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentIndex = arguments?.getInt(ARG_INDEX)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_film_data, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentIndex?.let { bind(view, it) }
    }

    /** Público: actualizar el detalle en tablets (2 paneles) */
    fun updateWithIndex(index: Int) {
        currentIndex = index
        view?.let { bind(it, index) }
    }

    private fun bind(view: View, index: Int) {
        val film = FilmRepository.films.getOrNull(index) ?: return
        view.findViewById<TextView>(R.id.txtTitle).text    = film.title
        view.findViewById<TextView>(R.id.txtYear).text     = film.year.toString()
        view.findViewById<TextView>(R.id.txtDirector).text = film.director
        view.findViewById<TextView>(R.id.txtNotes).text    = film.notes
    }
}
