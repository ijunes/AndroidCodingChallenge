package com.ijunes.androidchallenge.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import com.ijunes.androidchallenge.R

/**
 * Created by jkang on 5/15/18.
 */
object CommonUtil {

    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
            return it
        }
    }

}