package com.ijunes.androidchallenge.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ijunes.androidchallenge.data.Resource;
import com.ijunes.androidchallenge.ui.base.view.BaseAdapter;

import java.util.List;

public final class ListBindingAdapter{
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }
}