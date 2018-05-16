package com.ijunes.androidchallenge.ui.main.presenter

import com.ijunes.androidchallenge.ui.base.presenter.BasePresenter
import com.ijunes.androidchallenge.ui.main.interactor.MainMVPInteractor
import com.ijunes.androidchallenge.ui.main.view.MainMVPView
import com.ijunes.androidchallenge.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Presenter instance for MainActivity
 * Created by jkang on 5/15/18.
 */
class MainPresenter<V : MainMVPView, I : MainMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, compositeDisposable = disposable, schedulerProvider = schedulerProvider), MainMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.showProgress()
        fetchAllReviews()
    }

    override fun fetchAllReviews(){
        interactor?.let {
            compositeDisposable.add(it.getReviews()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({ reviewResponse ->
                        getView()?.hideProgress()
                        getView()?.loadReviews(reviewResponse)
                    }, { err ->
                        getView()?.hideProgress()
                        getView()?.showError()
                    }))
        }
    }

    override fun retry(){
        getView()?.hideError()
        fetchAllReviews()
    }

}