package com.sementa.plate.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sementa.plate.android.HomeView
import com.sementa.plate.android.VehicleView
import androidx.navigation.compose.composable
import com.sementa.plate.viewmodel.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeView(viewModel = homeViewModel, navController = navController)
        }
        composable(NavigationItem.VehicleDetail.route) {
            val vehicle = homeViewModel.vehicleState.value.vehicle
            if (vehicle != null) {
                navController.currentBackStackEntry?.savedStateHandle?.set("vehiclePlate", vehicle.registration)
                VehicleView(navController)
            }
        }
    }
}