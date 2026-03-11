package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugadorFormScreen(
    viewModel: JugadorDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSuccess) { if (state.isSuccess) onBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.jugadorId > 0) "Modificar Jugador" else "Crear Jugador") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Atrás") } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {

            if (state.errorGlobal != null) {
                Text(text = state.errorGlobal!!, color = MaterialTheme.colorScheme.error)
            }

            OutlinedTextField(
                value = state.nombres,
                onValueChange = { viewModel.onEvent(JugadorDetailUiEvent.OnNombresChange(it)) },
                label = { Text("Nombres *") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorMessageNombres != null,
                supportingText = { if (state.errorMessageNombres != null) Text(state.errorMessageNombres!!) }
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(JugadorDetailUiEvent.OnEmailChange(it)) },
                label = { Text("Email *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorMessageEmail != null,
                supportingText = { if (state.errorMessageEmail != null) Text(state.errorMessageEmail!!) }
            )

            Button(
                onClick = { viewModel.onEvent(JugadorDetailUiEvent.Save) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                else Text("Guardar")
            }
        }
    }
}