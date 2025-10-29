package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Lifecycle

class FilmListFragment : ListFragment() {

    interface OnFilmIndexSelectedListener {
        fun onFilmIndexSelected(index: Int)
    }

    private val films = listOf(
        "The Godfather",
        "Pulp Fiction",
        "Inception",
        "The Matrix",
        "Spirited Away",
        "Forest Gump"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, films)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_listado, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean = when (item.itemId) {
                R.id.action_add -> {
                    Toast.makeText(requireContext(), "Añadir pulsado", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), FilmEditActivity::class.java))
                    true
                }
                R.id.action_about -> {
                    Toast.makeText(requireContext(), "About pulsado", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), AboutActivity::class.java))
                    true
                }
                R.id.action_exit -> {
                    Toast.makeText(requireContext(), "Salir pulsado", Toast.LENGTH_SHORT).show()
                    requireActivity().finishAffinity()
                    true
                }
                else -> false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        (requireActivity() as? OnFilmIndexSelectedListener)?.onFilmIndexSelected(position)
    }
}
