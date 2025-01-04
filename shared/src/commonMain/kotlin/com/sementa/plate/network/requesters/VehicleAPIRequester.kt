package com.sementa.plate.network.requesters

import com.sementa.theoportfolio.network.generic.BaseAPIRequestBuilder
import com.sementa.theoportfolio.network.generic.NetworkPath
import io.ktor.http.HttpMethod

sealed class VehicleAPIRequester: BaseAPIRequestBuilder() {
    data class fetchFromPlate(val plate: String): VehicleAPIRequester()

    override val path: String
        get() = when (this) {
            is fetchFromPlate -> NetworkPath.Vehicle.fromPlate
        }

    override val httpMethod: HttpMethod
        get() = when (this) {
            is fetchFromPlate -> HttpMethod.Post
        }

    override val parameters: List<Pair<String, String>>?
        get() = null

    override val isTokenNeeded: Boolean
        get() = false

    override val body: Any?
        get() = when (this) {
            is fetchFromPlate -> mapOf("plate" to plate)
        }
}