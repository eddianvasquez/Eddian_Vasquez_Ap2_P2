package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.Resource
import edu.ucne.eddian_vasquez_ap2_p2.domain.usecase.DeleteJugadorUseCase
import edu.ucne.eddian_vasquez_ap2_p2.domain.usecase.GetJugadoresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorListViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase,
    private val deleteJugadorUseCase: DeleteJugadorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(JugadorListUiState())
    val state = _state.asStateFlow()

    init { loadJugadores() }

    fun onEvent(event: JugadorListUiEvent) {
        when (event) {
            JugadorListUiEvent.Refresh -> loadJugadores()
            is JugadorListUiEvent.Delete -> deleteJugador(event.id)
        }
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, jugadores = result.data ?: emptyList()) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    private fun deleteJugador(id: Int) {
        viewModelScope.launch {
            when (val result = deleteJugadorUseCase(id)) {
                is Resource.Success -> loadJugadores()
                is Resource.Error -> _state.update { it.copy(error = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }
}