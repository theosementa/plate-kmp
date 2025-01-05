package com.sementa.plate.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sementa.plate.domain.VehicleModel
import com.sementa.plate.stores.VehicleStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun VehicleView(navController: NavController) {
    val vehicleRegistration = navController.currentBackStackEntry?.savedStateHandle?.get<String>("vehicleRegistration")
    val vehicle = VehicleStore.shared.vehicles.collectAsState().value.firstOrNull {
        it.registration == vehicleRegistration
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row {
            TextButton(
                onClick = {  navController.popBackStack() },
                modifier = Modifier
            ) {
                Text("Retour")
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Text(text = "Vehicle Details", style = MaterialTheme.typography.headlineLarge)
        if (vehicle != null) {
            Text(text = "Brand: ${vehicle.brand}")
            Text(text = "Model: ${vehicle.model}")
        }
    }
}