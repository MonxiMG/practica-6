// es/ua/eps/filmoteca/StartActivity.kt
package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val btnVerListado = findViewById<Button>(R.id.btnVerListado)
        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        val goToList: () -> Unit = {
            startActivity(Intent(this, MainActivity::class.java))
        }

        tvTitulo.setOnClickListener { goToList() }
        btnVerListado.setOnClickListener { goToList() }

        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        btnSalir.setOnClickListener {
            // Cierra la app “de verdad”
            finishAffinity()
        }
    }
}
