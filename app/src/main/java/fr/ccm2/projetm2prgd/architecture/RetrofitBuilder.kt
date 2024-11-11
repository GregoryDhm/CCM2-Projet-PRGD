package fr.ccm2.projetm2prgd.architecture

import com.google.gson.GsonBuilder
import fr.ccm2.projetm2prgd.data.remote.CreditCardEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://random-data-api.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
    fun getCreditCard(): CreditCardEndpoint = retrofit.create(CreditCardEndpoint::class.java)

}