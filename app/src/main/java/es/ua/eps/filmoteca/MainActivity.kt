package es.ua.eps.filmoteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), FilmListFragment.OnFilmIndexSelectedListener {

    private val isTwoPane: Boolean
        get() = findViewById<android.view.View?>(R.id.detailContainer) != null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isTwoPane && savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, FilmListFragment())
            }
        }
    }

    override fun onFilmIndexSelected(index: Int) {
        if (isTwoPane) {
            // Actualiza el fragment de detalle estático
            (supportFragmentManager.findFragmentById(R.id.detailContainer) as? FilmDataFragment)
                ?.updateWithIndex(index)
        } else {
            // Navega al detalle en 1 panel
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainer, FilmDataFragment.newInstance(index))
                addToBackStack("detail")
            }
        }
    }
}
