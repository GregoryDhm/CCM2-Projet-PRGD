package fr.ccm2.projetm2prgd.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ccm2.projetm2prgd.data.model.CreditCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditCardDao {
    @Query("SELECT * FROM credit_card ORDER BY credit_card_type ASC")
    fun selectAll(): Flow<List<CreditCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(creditCardEntity: CreditCardEntity)

    @Query("DELETE FROM credit_card")
    fun deleteAll()
}
