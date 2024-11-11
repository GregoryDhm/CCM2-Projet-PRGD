package fr.ccm2.projetm2prgd.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ccm2.projetm2prgd.data.dao.CountryDao
import fr.ccm2.projetm2prgd.data.model.CountryEntity

@Database(
    entities = [
        CountryEntity::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
