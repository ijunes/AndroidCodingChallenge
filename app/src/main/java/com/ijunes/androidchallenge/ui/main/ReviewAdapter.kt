package com.ijunes.androidchallenge.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ijunes.androidchallenge.R
import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity
import com.ijunes.androidchallenge.dto.Review

/**
 * Created by jkang on 5/15/18.
 */
class ReviewAdapter(var reviews: ArrayList<ReviewEntity> = ArrayList()) : RecyclerView.Adapter<ReviewItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItem {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = ReviewItem(inflater.inflate(R.layout.review_item, parent, false))
        return viewHolder
    }

    override fun getItemCount(): Int =
        reviews.size


    override fun onBindViewHolder(holder: ReviewItem, position: Int) {
        holder.setupCard(reviews.get(position))
    }

    fun updateItems(newItems: List<ReviewEntity>, clear: Boolean = false){
        if(clear){
            reviews.clear()
        }
        reviews.addAll(newItems)
    }

}