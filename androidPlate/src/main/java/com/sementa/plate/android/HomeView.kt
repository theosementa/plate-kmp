package com.sementa.plate.android

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.sementa.plate.viewmodel.HomeViewModel

@Composable
fun HomeView(viewModel: HomeViewModel) {
    val vehicleState by viewModel.vehicleState.collectAsState()
    val plateInput by viewModel.plateInput.collectAsState()

    Column {
        TextField(
            value = plateInput,
            onValueChange = { newValue ->
                viewModel.updatePlateInput(newValue)
            },
            label = {
                Text(text = "Hello")
            }
        )
        
        TextButton(onClick = {
            println("Plaque: " + viewModel.plateInput.value)
            viewModel.fetchVehicleFromPlate()
        }) {
            Text(text = "Rechercher")
        }

        when {
            vehicleState.isLoading -> Text("Loading...")
            vehicleState.vehicle != null -> Text("Vehicle: ${vehicleState.vehicle}")
            else -> Text("No vehicle found")
        }
    }
}