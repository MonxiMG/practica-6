package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity(), FilmListFragment.OnFilmIndexSelectedListener {

    private var twoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1) Conecta la toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
            ?: error("No se encontró topAppBar en activity_main.xml.")
        setSupportActionBar(toolbar)

        // 2) Flecha manual y su click
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 3) Menú FORZADO en la toolbar (independiente del MenuProvider)
        toolbar.menu.clear()
        menuInflater.inflate(R.menu.menu_listado, toolbar.menu)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_add -> {
                    Toast.makeText(this, "Añadir pulsado (toolbar)", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, FilmEditActivity::class.java))
                    true
                }
                R.id.action_about -> {
                    Toast.makeText(this, "About pulsado (toolbar)", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                R.id.action_exit -> {
                    Toast.makeText(this, "Salir pulsado (toolbar)", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                    true
                }
                else -> false
            }
        }

        title = getString(R.string.app_name)

        // 4) Detecta dos paneles
        twoPane = findViewById<View?>(R.id.detail_container) != null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // Navegación lista → detalle
    override fun onFilmIndexSelected(index: Int) {
        val detail = FilmDataFragment().apply {
            arguments = Bundle().apply { putInt("film_index", index) }
        }
        if (twoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, detail)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, detail)
                .addToBackStack(null)
                .commit()
        }
    }
}
