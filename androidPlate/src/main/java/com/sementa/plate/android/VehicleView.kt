package com.sementa.plate.android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.sementa.plate.domain.VehicleModel
import kotlinx.serialization.json.Json

@Composable
fun VehicleView(navBackStackEntry: NavBackStackEntry) {
    val vehicleJson = navBackStackEntry.arguments?.getString("vehicleJson")
    val vehicle = vehicleJson?.let { Json.decodeFromString<VehicleModel>(it) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Vehicle Details", style = MaterialTheme.typography.headlineLarge)
        vehicle?.let {
            Text(text = "Brand: ${it.brand}")
            Text(text = "Model: ${it.model}")
            // Affichez d'autres détails du véhicule ici
        } ?: Text(text = "No vehicle details available.")
    }
}