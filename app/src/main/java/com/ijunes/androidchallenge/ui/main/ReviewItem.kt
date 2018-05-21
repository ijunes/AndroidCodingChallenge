package com.ijunes.androidchallenge.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ijunes.androidchallenge.R
import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity
import com.ijunes.androidchallenge.dto.Review
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.review_item.*

/**
 * Created by jkang on 5/15/18.
 */
class ReviewItem(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    // Setup card details when recyclerview binds item
    fun setupCard(reviewItem: ReviewEntity){
        reviewTitle.text = reviewItem.displayTitle
        movieRating.text = if(reviewItem.mpaaRating.isNullOrEmpty()) containerView?.context?.getString(R.string.nr) else reviewItem.mpaaRating
        reviewBy.text = reviewItem.byline
        reviewDate.text = reviewItem.publicationDate
        containerView?.let{
            // Choice to use glide for simplicity
            Glide.with(it.context).load(reviewItem.multimedia.src).apply(RequestOptions.centerCropTransform()).into(reviewImage)
        }
        movieHeadline.text = reviewItem.headline
        movieSummary.text = reviewItem.summaryShort
    }
}