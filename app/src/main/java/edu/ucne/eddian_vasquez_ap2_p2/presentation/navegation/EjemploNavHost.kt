package edu.ucne.eddian_vasquez_ap2_p2.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.eddian_vasquez_ap2_p2.presentation.api.detail.DetailScreen
import edu.ucne.eddian_vasquez_ap2_p2.presentation.api.list.ListScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListScreen(
                onItemClick = { id ->
                    navHostController.navigate(Screen.Detail(id))
                }
            )
        }

        composable<Screen.Detail> {
            DetailScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}