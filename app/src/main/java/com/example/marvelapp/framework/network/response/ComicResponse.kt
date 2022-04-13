package com.example.marvelapp.framework.network.response

import br.com.msmlabs.core.domain.model.Comics
import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse
)

fun ComicResponse.toComicModel(): Comics {
    return Comics(
        id = this.id,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}".replace("http", "https")
    )
}