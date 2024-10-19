package fr.ccm2.projetm2prgd.data.remote

import fr.ccm2.projetm2prgd.data.model.CountryObjectDto
import retrofit2.http.GET

interface RestCountriesEndpoint {
    @GET("all?fields=name,region")
    suspend fun getCountries() : List<CountryObjectDto>
}