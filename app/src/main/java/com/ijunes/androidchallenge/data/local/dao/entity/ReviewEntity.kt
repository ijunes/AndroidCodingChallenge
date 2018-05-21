package com.ijunes.androidchallenge.data.local.dao.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName
import com.ijunes.androidchallenge.dto.Link
import com.ijunes.androidchallenge.dto.Multimedia

@Entity(tableName = "reviews")
class ReviewEntity(@PrimaryKey @SerializedName("date_updated") val dateUpdated: String,
                   @SerializedName("display_title") val displayTitle: String,
                   @SerializedName("mpaa_rating") val mpaaRating: String?,
                   @SerializedName("critics_pick") val criticsPick: Int,
                   @SerializedName("byline") val byline: String,
                   @SerializedName("headline") val headline: String,
                   @SerializedName("summary_short") val summaryShort: String,
                   @SerializedName("publication_date") val publicationDate: String,
                   @SerializedName("opening_date") val openingDate: String,
                   @SerializedName("link") val link: Link,
                   @SerializedName("multimedia") val multimedia: Multimedia) {

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var isAdult: Boolean = false

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null
}