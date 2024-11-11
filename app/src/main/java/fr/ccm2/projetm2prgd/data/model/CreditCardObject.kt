package fr.ccm2.projetm2prgd.data.model

data class CreditCardObject(
    val number: String,
    val expiryDate: String,
    val type: String,
)
fun List<CreditCardEntity>.toUi(): List<CreditCardObject> {
    return map { eachEntity ->
        CreditCardObject(
            number = eachEntity.creditCardNumber,
            expiryDate = eachEntity.creditCardExpiryDate,
            type = eachEntity.creditCardType
        )
    }
}