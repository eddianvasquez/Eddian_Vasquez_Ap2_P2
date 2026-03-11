package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list

sealed interface JugadorListUiEvent {
    data object Refresh : JugadorListUiEvent
    data class Delete(val id: Int) : JugadorListUiEvent
}