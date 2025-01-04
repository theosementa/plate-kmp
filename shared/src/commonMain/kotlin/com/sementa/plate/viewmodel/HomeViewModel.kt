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

    private val _vehicleState: MutableStateFlow<VehicleState> = MutableStateFlow(VehicleState(isLoading = true))
    val vehicleState: StateFlow<VehicleState> get() = _vehicleState.asStateFlow()

    fun fetchVehicleFromPlate(plate: String) {
        scope.launch {
            _vehicleState.emit(VehicleState(isLoading = true))
            val response: VehicleModel = VehicleService().fetchVehicleFromPlate(plate)
            _vehicleState.emit(VehicleState(vehicle = response))
        }
    }

}