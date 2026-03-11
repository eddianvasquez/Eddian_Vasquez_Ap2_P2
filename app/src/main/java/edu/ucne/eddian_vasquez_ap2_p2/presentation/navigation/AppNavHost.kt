package edu.ucne.eddian_vasquez_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail.JugadorFormScreen
import edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list.JugadorListScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.JugadorList) {

        composable<Screen.JugadorList> {
            JugadorListScreen(
                onItemClick = { id -> navHostController.navigate(Screen.JugadorForm(id)) },
                onCreateClick = { navHostController.navigate(Screen.JugadorForm(0)) }
            )
        }

        composable<Screen.JugadorForm> {
            JugadorFormScreen(onBack = { navHostController.navigateUp() })
        }
    }
}