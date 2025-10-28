package es.ua.eps.filmoteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), FilmListFragment.OnFilmSelectedListener {

    private val isTwoPane: Boolean
        get() = findViewById<android.view.View?>(R.id.detailContainer) != null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // En 1-panel, si no hay fragment cargado (primer arranque), muestra la lista
        if (!isTwoPane && savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, FilmListFragment())
            }
        }
        // En 2-panel, los FragmentContainerView del layout ya instancian lista y detalle vacíos.
    }

    override fun onFilmSelected(film: Film) {
        if (isTwoPane) {
            // Reemplaza solo el panel de detalle
            supportFragmentManager.commit {
                replace(R.id.detailContainer, FilmDataFragment.newInstance(film))
            }
        } else {
            // Navega al detalle en el único contenedor y añade al back stack
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, FilmDataFragment.newInstance(film))
                addToBackStack("detail")
            }
        }
    }
}
