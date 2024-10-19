package fr.ccm2.projetm2prgd.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryObjectDto(
    @Expose
    @SerializedName("name")
    val name: CountryNameDto,

    @Expose
    @SerializedName("region")
    val region: String
)
data class CountryNameDto(
    @Expose
    @SerializedName("common")
    val common: String,
)