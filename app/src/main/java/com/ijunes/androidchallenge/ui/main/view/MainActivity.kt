package com.ijunes.androidchallenge.ui.main.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ijunes.androidchallenge.R
import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.ui.base.view.BaseActivity
import com.ijunes.androidchallenge.ui.main.ReviewAdapter
import com.ijunes.androidchallenge.ui.main.interactor.MainMVPInteractor
import com.ijunes.androidchallenge.ui.main.presenter.MainMVPPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

/**
 * Created by jkang on 05/15/18.
 */
class MainActivity : BaseActivity(), MainMVPView {

    companion object {
        const val TAG = "MainActivity"
        const val VIEW_CACHE_SIZE = 6
    }

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ReviewAdapter(ArrayList())
        recyclerView.setHasFixedSize(false)
        recyclerView.setItemViewCacheSize(VIEW_CACHE_SIZE)
        refreshLayout.setOnRefreshListener { presenter.fetchAllReviews() }
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this)
    }

    /**
     * Initial loading of items
     */
    override fun loadReviews(reviewResponse: ReviewResponse) {
        if (errorLayout.visibility == View.VISIBLE) {
            hideError()
        }
        (recyclerView.adapter as ReviewAdapter).updateItems(reviewResponse.results, true)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun addMoreReviews(reviewResponse: ReviewResponse) {
        var shouldClear = false
        if (errorLayout.visibility == View.VISIBLE) {
            hideError()
            shouldClear = true
        }
        (recyclerView.adapter as ReviewAdapter).updateItems(reviewResponse.results, shouldClear)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun showError() {
        errorLayout.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorLayout.visibility = View.GONE
    }

    override fun hideProgress() {
        super.hideProgress()
        refreshLayout.isRefreshing = false
    }
}
