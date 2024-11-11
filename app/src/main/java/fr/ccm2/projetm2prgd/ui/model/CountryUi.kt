package fr.ccm2.projetm2prgd.ui.model

import fr.ccm2.projetm2prgd.data.model.CountryObject

sealed interface CountryUi {
    data class Item(
        val name: String,
        val region: String,
    ) : CountryUi

    data class Header(
        val title: String,
    ) : CountryUi
    data class Footer(
        val footer: Int,
    ) : CountryUi
    data class FooterTotal(
        val footerTotal: Int,
    ) : CountryUi
}
fun List<CountryObject>.toUi(): List<CountryUi.Item> {
    return map { currentObject ->
        CountryUi.Item(
            name = currentObject.commonName,
            region = currentObject.region,
        )
    }
}
