package com.sementa.theoportfolio.network.generic

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient: HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}
class NetworkService {
    suspend inline fun <reified T> sendRequest(builder: APIRequestBuilder): T {
        return try {
            httpClient.request(NetworkPath.base + builder.path) {
                method = builder.httpMethod

                builder.headers?.forEach { (key, value) ->
                    header(key, value)
                }

                builder.parameters?.forEach { (key, value) ->
                    parameter(key, value)
                }

                builder.body?.let { setBody(it) }
            }.body()
        } catch (e: Exception) {
            println("Erreur lors de la requête : ${e.message}")
            throw e
        }
    }
}