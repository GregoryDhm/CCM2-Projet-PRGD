package fr.ccm2.projetm2prgd.data.remote

import fr.ccm2.projetm2prgd.data.model.CreditCardDto
import retrofit2.http.GET

interface CreditCardEndpoint {
    @GET("credit_cards")
    suspend fun getRandomCreditCard() : CreditCardDto
}