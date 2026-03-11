package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail

sealed interface JugadorDetailUiEvent {
    data class OnNombresChange(val value: String) : JugadorDetailUiEvent
    data class OnEmailChange(val value: String) : JugadorDetailUiEvent
    data object Save : JugadorDetailUiEvent
}