package es.ua.eps.filmoteca

data class Film(
    val title: String,
    val year: Int,
    val director: String,
    val notes: String = ""
)
