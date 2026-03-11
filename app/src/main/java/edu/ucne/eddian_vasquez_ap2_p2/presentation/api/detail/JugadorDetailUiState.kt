package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail

data class JugadorDetailUiState(
    val isLoading: Boolean = false,
    val jugadorId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val errorMessageNombres: String? = null,
    val errorMessageEmail: String? = null,
    val errorGlobal: String? = null,
    val isSuccess: Boolean = false
)