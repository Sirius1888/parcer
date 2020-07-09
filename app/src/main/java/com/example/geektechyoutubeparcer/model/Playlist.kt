package com.example.geektechyoutubeparcer.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("kind") var kind: String,
    @SerializedName("pageInfo") var pageInfo: PageInfo,
    @SerializedName("etag") var etag: String,
    @SerializedName("items") var items: MutableList<PlaylistItem>

)

data class High(
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("height")
    val height: Int? = null
)


data class PageInfo(
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("resultsPerPage")
    val resultsPerPage: Int? = null
)


data class ContentDetails(
    @SerializedName("itemCount")
    val itemCount: String? = null
)


data class Medium(
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("height")
    val height: Int? = null
)


data class Maxres(
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("height")
    val height: Int? = null
)

data class Standard(
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("height")
    val height: Int? = null
)

data class Thumbnails(
    @SerializedName("standard")
    val standard: Standard? = null,
    @SerializedName("default")
    val default: Default? = null,
    @SerializedName("high")
    val high: High? = null,
    @SerializedName("maxres")
    val maxres: Maxres? = null,
    @SerializedName("medium")
    val medium: Medium? = null
)

data class PlaylistItem(
    @SerializedName("snippet")
    val snippet: Snippet? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("contentDetails")
    val contentDetails: ContentDetails? = null
)

data class Snippet(
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("localized")
    val localized: Localized? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails? = null,
    @SerializedName("channelId")
    val channelId: String? = null,
    @SerializedName("channelTitle")
    val channelTitle: String? = null,
    @SerializedName("resourceId")
    val resourceId: ResourceId? = null,
    @SerializedName("playlistId")
    val playlistId: String? = null
)

data class Default(
    @SerializedName("width")
    val width: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("height")
    val height: Int
)

data class Localized(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)

data class ResourceId(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("videoId")
    val videoId: String
)