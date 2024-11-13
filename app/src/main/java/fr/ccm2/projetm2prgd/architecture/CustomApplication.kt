package fr.ccm2.projetm2prgd.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication : Application() {

    companion object {
        lateinit var instance: CustomApplication
    }

    val mApplicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            this,
            CustomRoomDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}