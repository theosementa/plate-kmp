package com.sementa.plate.network.services

import com.sementa.plate.domain.VehicleModel
import com.sementa.plate.network.requesters.VehicleAPIRequester
import com.sementa.theoportfolio.network.generic.NetworkService

class VehicleService {

    suspend fun fetchVehicleFromPlate(plate: String): VehicleModel {
        return try {
            NetworkService().sendRequest(VehicleAPIRequester.fetchFromPlate(plate))
        } catch (e: Exception) {
            throw e
        }
    }

}