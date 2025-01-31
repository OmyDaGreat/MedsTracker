package xyz.malefic.meds.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.Flow
import xyz.malefic.meds.data.MuseumObject
import xyz.malefic.meds.data.MuseumRepository

class DetailScreenModel(
    private val museumRepository: MuseumRepository,
) : ScreenModel {
    fun getObject(objectId: Int): Flow<MuseumObject?> = museumRepository.getObjectById(objectId)
}
