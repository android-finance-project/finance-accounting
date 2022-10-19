package ru.rt.finance.features.exchangerates.data.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

/**
 * Класс для сборки клиентов
 *
 * @property baseUrl url источника данных
 */
class HttpClientBuilder(private val baseUrl: String) {

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val httpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json
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