package fr.ccm2.projetm2prgd.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credit_card")
data class CreditCardEntity(
    @ColumnInfo(name = "credit_card_number")
    val creditCardNumber: String,

    @ColumnInfo(name = "credit_card_expiry_date")
    val creditCardExpiryDate: String,

    @ColumnInfo(name = "credit_card_type")
    val creditCardType: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}