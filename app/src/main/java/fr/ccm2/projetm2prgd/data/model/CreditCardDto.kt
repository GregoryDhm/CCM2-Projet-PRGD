package fr.ccm2.projetm2prgd.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreditCardDto(
    @Expose
    @SerializedName("credit_card_number")
    val creditCardNumber: String,

    @Expose
    @SerializedName("credit_card_expiry_date")
    val creditCardExpiryDate: String,

    @Expose
    @SerializedName("credit_card_type")
    val creditCardType: String
)
fun CreditCardDto.toRoom(): CreditCardEntity {
    return CreditCardEntity(
        creditCardNumber = creditCardNumber,
        creditCardExpiryDate = creditCardExpiryDate,
        creditCardType = creditCardType
    )
}