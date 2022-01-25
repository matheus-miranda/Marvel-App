package br.com.msmlabs.core.data.network.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail") val thumbnail: String
)