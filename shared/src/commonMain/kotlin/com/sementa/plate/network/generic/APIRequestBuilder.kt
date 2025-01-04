package com.sementa.theoportfolio.network.generic

import com.sementa.theoportfolio.network.managers.TokenManager
import io.ktor.http.HttpMethod

interface APIRequestBuilder {
    val path: String
    val httpMethod: HttpMethod
    val parameters: List<Pair<String, String>>?
    val isTokenNeeded: Boolean
    val headers: List<Pair<String, String>>?
    val body: Any?
}
abstract class BaseAPIRequestBuilder : APIRequestBuilder {
    override val headers: List<Pair<String, String>>?
        get() {
            val headersList = mutableListOf(
                "Content-Type" to "application/json",
            )

            if (isTokenNeeded) {
                headersList.add("Authorization" to "Bearer ${TokenManager.token}")
            }

            return headersList
        }
}

