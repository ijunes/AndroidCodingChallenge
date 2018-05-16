package com.ijunes.androidchallenge.ui.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ijunes.androidchallenge.util.CommonUtil
import dagger.android.AndroidInjection

/**
 * Created by jkang on 5/15/18.
 */
abstract class BaseActivity : AppCompatActivity(), MVPView {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    private fun performDI() = AndroidInjection.inject(this)

}