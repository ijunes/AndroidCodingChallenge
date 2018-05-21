package com.ijunes.androidchallenge.ui.base.view

import android.app.ProgressDialog
import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ijunes.androidchallenge.util.CommonUtil
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.LifecycleRegistry





/**
 * Created by jkang on 5/15/18.
 */
abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : AppCompatActivity(), MVPView {

    private val lifecycleRegistry = LifecycleRegistry(this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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