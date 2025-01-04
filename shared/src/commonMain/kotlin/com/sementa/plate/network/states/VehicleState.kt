package com.sementa.plate.network.states

import com.sementa.plate.domain.VehicleModel

data class VehicleState(
    val vehicle: VehicleModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
}