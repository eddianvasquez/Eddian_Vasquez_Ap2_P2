package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()
}