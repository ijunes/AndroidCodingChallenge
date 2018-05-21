package com.ijunes.androidchallenge.ui.base.view

import android.support.v7.widget.RecyclerView

/**
 * Created by jkang on 5/20/18.
 */
abstract class BaseAdapter<Type : RecyclerView.ViewHolder, Data> : RecyclerView.Adapter<Type>() {

    abstract fun setData(data: List<Data>)
}