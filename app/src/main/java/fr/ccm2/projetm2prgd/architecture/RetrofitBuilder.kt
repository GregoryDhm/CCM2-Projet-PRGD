package fr.ccm2.projetm2prgd.architecture

import com.google.gson.GsonBuilder
import fr.ccm2.projetm2prgd.data.remote.RestCountriesEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
    fun getCountries(): RestCountriesEndpoint = retrofit.create(RestCountriesEndpoint::class.java)

}