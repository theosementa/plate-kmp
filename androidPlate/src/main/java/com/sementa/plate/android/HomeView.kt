package com.sementa.plate.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.sementa.plate.android.utilities.Background100
import com.sementa.plate.android.utilities.Background300
import com.sementa.plate.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeView(viewModel: HomeViewModel) {
    val vehicleState by viewModel.vehicleState.collectAsState()
    val plateInput by viewModel.plateInput.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.Background100, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            BasicTextField(
                value = plateInput.uppercase(),
                onValueChange = { viewModel.updatePlateInput(it) },
                modifier = Modifier
                    .fillMaxWidth(1f),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                cursorBrush = SolidColor(Color.White)
            )
            
            if (plateInput.isEmpty()) {
                Text(
                    text = "Entrer une plaque...",
                    color = Color.Background300
                )
            }
        }
        
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