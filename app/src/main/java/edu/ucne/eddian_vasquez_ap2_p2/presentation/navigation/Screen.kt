package edu.ucne.eddian_vasquez_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object JugadorList : Screen()

    @Serializable
    data class JugadorForm(val id: Int) : Screen()
}