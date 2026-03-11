package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list

import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador

data class JugadorListUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null
)