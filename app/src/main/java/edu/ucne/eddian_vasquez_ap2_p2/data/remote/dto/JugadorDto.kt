package edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto

import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador

data class JugadorDto(
    val jugadorId: Int?,
    val nombres: String?,
    val email: String?
) {
    fun toDomain() = Jugador(
        jugadorId = jugadorId ?: 0,
        nombres = nombres ?: "",
        email = email ?: ""
    )
}

fun Jugador.toDto() = JugadorDto(
    jugadorId = if (this.jugadorId == 0) null else this.jugadorId,
    nombres = this.nombres,
    email = this.email
)