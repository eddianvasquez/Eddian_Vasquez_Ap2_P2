package edu.ucne.eddian_vasquez_ap2_p2.presentation.navegation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object List : Screen()

    @Serializable
    data class Detail(val id: Int) : Screen()
}