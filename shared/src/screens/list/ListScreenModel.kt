package xyz.malefic.meds.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import xyz.malefic.meds.data.MuseumObject
import xyz.malefic.meds.data.MuseumRepository

class ListScreenModel(
    museumRepository: MuseumRepository,
) : ScreenModel {
    val objects: StateFlow<List<MuseumObject>> =
        museumRepository
            .getObjects()
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
