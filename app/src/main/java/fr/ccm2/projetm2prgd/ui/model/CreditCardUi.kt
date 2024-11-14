package fr.ccm2.projetm2prgd.ui.model

import fr.ccm2.projetm2prgd.data.model.CreditCardObject

sealed interface CreditCardUi {
    data class Item(
        val number: String,
        val expiryDate: String,
        val type: String,
    ) : CreditCardUi

    data class Header(
        val title: String,
    ) : CreditCardUi

    data class Footer(
        val footer: Int,
    ) : CreditCardUi

    data class FooterTotal(
        val footerTotal: Int,
    ) : CreditCardUi

}

fun List<CreditCardObject>.toUi() : List<CreditCardUi> {
    return map { item ->
        CreditCardUi.Item(
            number = item.number,
            expiryDate = item.expiryDate,
            type = item.type,
        )
    }
}