package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.Resource
import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador
import edu.ucne.eddian_vasquez_ap2_p2.domain.usecase.GetJugadorByIdUseCase
import edu.ucne.eddian_vasquez_ap2_p2.domain.usecase.SaveJugadorUseCase
import edu.ucne.eddian_vasquez_ap2_p2.domain.usecase.UpdateJugadorUseCase
import edu.ucne.eddian_vasquez_ap2_p2.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorDetailViewModel @Inject constructor(
    private val getJugadorByIdUseCase: GetJugadorByIdUseCase,
    private val saveJugadorUseCase: SaveJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(JugadorDetailUiState())
    val state = _state.asStateFlow()

    private val routeArgs = savedStateHandle.toRoute<Screen.JugadorForm>()
    private val currentId = routeArgs.id

    init {
        if (currentId > 0) loadJugador(currentId)
    }

    fun onEvent(event: JugadorDetailUiEvent) {
        when (event) {
            is JugadorDetailUiEvent.OnNombresChange -> _state.update { it.copy(nombres = event.value, errorMessageNombres = null) }
            is JugadorDetailUiEvent.OnEmailChange -> _state.update { it.copy(email = event.value, errorMessageEmail = null) }
            JugadorDetailUiEvent.Save -> onSave()
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorByIdUseCase(id).collect { result ->
                if (result is Resource.Success) {
                    result.data?.let { j ->
                        _state.update {
                            it.copy(
                                isLoading = false, jugadorId = j.jugadorId,
                                nombres = j.nombres, email = j.email
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        val current = _state.value
        var isValid = true

        if (current.nombres.isBlank()) {
            _state.update { it.copy(errorMessageNombres = "El campo Nombres no puede estar vacío") }
            isValid = false
        }

        if (current.email.isBlank() || !current.email.contains("@")) {
            _state.update { it.copy(errorMessageEmail = "El Email es obligatorio y debe contener '@'") }
            isValid = false
        }

        return isValid
    }

    private fun onSave() {
        if (!validateFields()) return

        val current = _state.value
        val jugador = Jugador(
            jugadorId = currentId,
            nombres = current.nombres,
            email = current.email
        )

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorGlobal = null) }
            val result = if (currentId > 0) updateJugadorUseCase(currentId, jugador) else saveJugadorUseCase(jugador)

            when (result) {
                is Resource.Success -> _state.update { it.copy(isLoading = false, isSuccess = true) }
                is Resource.Error -> _state.update { it.copy(isLoading = false, errorGlobal = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }
}