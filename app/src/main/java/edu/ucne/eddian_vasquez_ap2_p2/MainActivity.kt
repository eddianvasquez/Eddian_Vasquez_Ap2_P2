package edu.ucne.eddian_vasquez_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.eddian_vasquez_ap2_p2.presentation.navegation.AppNavHost
import edu.ucne.eddian_vasquez_ap2_p2.ui.theme.Eddian_Vasquez_Ap2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Eddian_Vasquez_Ap2_P2Theme {

                val navController = rememberNavController()


                AppNavHost(navHostController = navController)
            }
        }
    }
}