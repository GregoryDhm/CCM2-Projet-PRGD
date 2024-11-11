package fr.ccm2.projetm2prgd.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ccm2.projetm2prgd.data.dao.CreditCardDao
import fr.ccm2.projetm2prgd.data.model.CreditCardEntity

@Database(
    entities = [
        CreditCardEntity::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun creditCardDao(): CreditCardDao
}
