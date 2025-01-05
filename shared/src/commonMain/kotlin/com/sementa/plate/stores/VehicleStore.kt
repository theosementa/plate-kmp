package com.sementa.plate.stores

import com.sementa.plate.domain.VehicleModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VehicleStore {
    companion object {
        val shared = VehicleStore()
    }

    private val _vehicles: MutableStateFlow<List<VehicleModel>> = MutableStateFlow(listOf())
    val vehicles: StateFlow<List<VehicleModel>> get() = _vehicles

    suspend fun addVehicle(vehicle: VehicleModel) {
        _vehicles.emit(vehicles.value + vehicle)
    }
}
