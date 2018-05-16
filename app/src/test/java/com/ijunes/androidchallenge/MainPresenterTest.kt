package com.ijunes.androidchallenge

import com.ijunes.androidchallenge.dto.Review
import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.ui.main.interactor.MainInteractor
import com.ijunes.androidchallenge.ui.main.presenter.MainPresenter
import com.ijunes.androidchallenge.ui.main.view.MainMVPView
import com.ijunes.androidchallenge.util.SchedulerProvider
import com.ijunes.androidchallenge.util.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.verification.VerificationMode
import org.mockito.Mockito.`when` as whenever

/**
 * Created by jkang on 5/16/18.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest{

    @Mock
    lateinit var mockInteractor: MainInteractor

    @Mock
    lateinit var mockView: MainMVPView

    lateinit var presenter: MainPresenter<MainMVPView, MainInteractor>
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        compositeDisposable = CompositeDisposable()
        testSchedulerProvider = TestSchedulerProvider()
        presenter = MainPresenter(interactor = mockInteractor, schedulerProvider = testSchedulerProvider, disposable = compositeDisposable)
    }


    @Test
    fun testGetReviewsError(){
        // Given
        val error = "Test error"
        val single: Single<ReviewResponse> = Single.create {
            emitter ->
            emitter.onError(Exception(error))
        }

        // When
        whenever(mockInteractor.getReviews()).thenReturn(single)

        presenter.onAttach(mockView)
        verify(mockView).showProgress()

        testSchedulerProvider.testScheduler.triggerActions()

        // Then
        verify(mockView).hideProgress()
        verify(mockView).showError()
    }

    @Test
    fun testGetReviewsErrorRecovery(){
        // Given
        val error = "Test error"
        val validResponse = ReviewResponse(copyright = "copyright", hasMore = false, numResults = 20, results = ArrayList<Review>(), status="Test")
        val invalidSingle: Single<ReviewResponse> = Single.create {
            emitter ->
            emitter.onError(Exception(error))
        }
        val validSingle: Single<ReviewResponse> = Single.create {
            emitter ->
            emitter.onSuccess(validResponse)
        }
        whenever(mockInteractor.getReviews()).thenReturn(invalidSingle)

        presenter.onAttach(mockView)
        testSchedulerProvider.testScheduler.triggerActions()

        // When
        whenever(mockInteractor.getReviews()).thenReturn(validSingle)
        presenter.retry()

        testSchedulerProvider.testScheduler.triggerActions()

        // Then
        verify(mockView).loadReviews(validResponse)
        verify(mockView, atMost(2)).showProgress()
        verify(mockView, atMost(2)).hideProgress()
        verify(mockView).showError()
    }

    @Test
    fun testGetEmptyReviews() {
        // Given
        val reviewResponse = ReviewResponse(copyright = "copyright", hasMore = false, numResults = 20, results = ArrayList<Review>(), status="Test")
        val single: Single<ReviewResponse> = Single.create {
            emitter ->
            emitter.onSuccess(reviewResponse)
        }

        // When
        whenever(mockInteractor.getReviews()).thenReturn(single)

        presenter.onAttach(mockView)

        testSchedulerProvider.testScheduler.triggerActions()

        // Then
        verify(mockView).hideProgress()
        verify(mockView).loadReviews(reviewResponse)
    }


}