package es.ua.eps.filmoteca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FilmDataFragment : Fragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_YEAR = "arg_year"
        private const val ARG_DIRECTOR = "arg_director"
        private const val ARG_NOTES = "arg_notes"

        fun newInstance(film: Film) = FilmDataFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, film.title)
                putInt(ARG_YEAR, film.year)
                putString(ARG_DIRECTOR, film.director)
                putString(ARG_NOTES, film.notes)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_film_data, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtTitle).text    = requireArguments().getString(ARG_TITLE)
        view.findViewById<TextView>(R.id.txtYear).text     = requireArguments().getInt(ARG_YEAR).toString()
        view.findViewById<TextView>(R.id.txtDirector).text = requireArguments().getString(ARG_DIRECTOR)
        view.findViewById<TextView>(R.id.txtNotes).text    = requireArguments().getString(ARG_NOTES)
    }
}
