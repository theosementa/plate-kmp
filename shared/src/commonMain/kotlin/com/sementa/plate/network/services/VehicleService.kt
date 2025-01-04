package com.sementa.plate.network.services

import com.sementa.plate.domain.VehicleModel
import com.sementa.plate.network.requesters.VehicleAPIRequester
import com.sementa.theoportfolio.network.generic.NetworkService

class VehicleService {

    suspend fun fetchVehicleFromPlate(plate: String): VehicleModel {
        return NetworkService().sendRequest(VehicleAPIRequester.fetchFromPlate(plate))
    }

}