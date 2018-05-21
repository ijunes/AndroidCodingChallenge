package com.ijunes.androidchallenge.dto
import com.google.gson.annotations.SerializedName
import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity


/**
 * Created by jkang on 5/15/18.
 */

data class ReviewResponse(
		@SerializedName("status") val status: String,
		@SerializedName("copyright") val copyright: String,
		@SerializedName("has_more") val hasMore: Boolean,
		@SerializedName("num_results") val numResults: Int,
		@SerializedName("results") val results: List<ReviewEntity>
)

data class Review(
		@SerializedName("display_title") val displayTitle: String,
		@SerializedName("mpaa_rating") val mpaaRating: String?,
		@SerializedName("critics_pick") val criticsPick: Int,
		@SerializedName("byline") val byline: String,
		@SerializedName("headline") val headline: String,
		@SerializedName("summary_short") val summaryShort: String,
		@SerializedName("publication_date") val publicationDate: String,
		@SerializedName("opening_date") val openingDate: String,
		@SerializedName("date_updated") val dateUpdated: String,
		@SerializedName("link") val link: Link,
		@SerializedName("multimedia") val multimedia: Multimedia
)

data class Multimedia(
		@SerializedName("type") val type: String,
		@SerializedName("src") val src: String,
		@SerializedName("width") val width: Int,
		@SerializedName("height") val height: Int
)

data class Link(
		@SerializedName("type") val type: String,
		@SerializedName("url") val url: String,
		@SerializedName("suggested_link_text") val suggestedLinkText: String
)