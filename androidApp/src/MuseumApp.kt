package xyz.malefic.meds

import android.app.Application
import xyz.malefic.meds.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
