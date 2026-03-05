package edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Lista") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { onItemClick(1) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar nuevo"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}