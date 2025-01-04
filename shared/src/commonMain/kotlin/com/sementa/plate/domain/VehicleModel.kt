package com.sementa.plate.domain

import kotlinx.serialization.Serializable

@Serializable
data class VehicleModel(
    val id: String? = null,
    val brand: String? = null,
    val model: String? = null,
    val version: String? = null,
    val bodywork: String? = null,
    val nationalType: String? = null,
    val nbDoor: Int? = null,
    val vin: String? = null,
    val energy: String? = null,
    val engine: String? = null,
    val color: String? = null,
    val registration: String? = null,
    val powerCV: Int? = null,
    val powerKW: Int? = null,
    val turbo: Boolean? = null,
    val gearbox: String? = null,
    val propulsion: String? = null,
    val circulationDate: String? = null,
    val serialNumber: String? = null
) {
}