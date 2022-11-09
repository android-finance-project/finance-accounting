package ru.rt.finance.features.exchangerates.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Класс для сборки клиентов
 *
 * @property baseUrl url источника данных
 */
class HttpClientBuilder(private val baseUrl: String) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val httpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }

            defaultRequest {
                host = baseUrl
                url {
                    protocol = URLProtocol.HTTPS
                }
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}