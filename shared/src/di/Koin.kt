package xyz.malefic.meds.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import xyz.malefic.meds.data.InMemoryMuseumStorage
import xyz.malefic.meds.data.KtorMuseumApi
import xyz.malefic.meds.data.MuseumApi
import xyz.malefic.meds.data.MuseumRepository
import xyz.malefic.meds.data.MuseumStorage
import xyz.malefic.meds.screens.detail.DetailScreenModel
import xyz.malefic.meds.screens.list.ListScreenModel

val dataModule =
    module {
        single {
            val json = Json { ignoreUnknownKeys = true }
            HttpClient {
                install(ContentNegotiation) {
                    // TODO Fix API so it serves application/json
                    json(json, contentType = ContentType.Any)
                }
            }
        }

        single<MuseumApi> { KtorMuseumApi(get()) }
        single<MuseumStorage> { InMemoryMuseumStorage() }
        single {
            MuseumRepository(get(), get()).apply {
                initialize()
            }
        }
    }

val screenModelsModule =
    module {
        factoryOf(::ListScreenModel)
        factoryOf(::DetailScreenModel)
    }

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}
