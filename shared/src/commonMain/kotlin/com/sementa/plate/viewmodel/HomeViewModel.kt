package com.sementa.plate.viewmodel

import com.sementa.plate.domain.VehicleModel
import com.sementa.plate.network.services.VehicleService
import com.sementa.plate.network.states.VehicleState
import com.sementa.plate.utilities.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() {

    private val _plateInput: MutableStateFlow<String> = MutableStateFlow("")
    val plateInput: StateFlow<String> get() = _plateInput.asStateFlow()

    private val _vehicleState: MutableStateFlow<VehicleState> = MutableStateFlow(VehicleState())
    val vehicleState: StateFlow<VehicleState> get() = _vehicleState.asStateFlow()

    fun updatePlateInput(newInput: String) {
        _plateInput.value = newInput
    }

    fun fetchVehicleFromPlate() {
        scope.launch {
            _vehicleState.emit(VehicleState(isLoading = true))
            try {
                val response: VehicleModel = VehicleService().fetchVehicleFromPlate(plateInput.value)
                _vehicleState.emit(VehicleState(vehicle = response))
            } catch (e: Exception) {
                _vehicleState.emit(VehicleState(error = e.message))
            }
        }
    }

}