package com.pixabay.testtask.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixabay.testtask.R
import com.pixabay.testtask.data.datasource.PixabayDataSource
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import com.pixabay.testtask.util.ResourceUtil
import com.pixabay.testtask.util.Result
import com.pixabay.testtask.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class FeedViewModel @Inject constructor (
    private val schedulerProvider: SchedulerProvider,
    private val pixabayDataSource: PixabayDataSource,
    private val resourceUtil: ResourceUtil
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var currentPage = 0
    private var currentSearchText = ""

    private val mutableImagesLiveData: MutableLiveData<Result<List<PixabayImage>>> = MutableLiveData()
    val imagesLiveData: LiveData<Result<List<PixabayImage>>> get() = mutableImagesLiveData

    private val mutableErrorLiveData: MutableLiveData<Result<Throwable>> = MutableLiveData()
    val errorLiveData: LiveData<Result<Throwable>> get() = mutableErrorLiveData

    private val mutableMessageLiveData: MutableLiveData<Result<String>> = MutableLiveData()
    val messageLiveData: LiveData<Result<String>> get() = mutableMessageLiveData

    private val mutableProgressLiveData: MutableLiveData<Result<Nothing>> = MutableLiveData()
    val progressLiveData: LiveData<Result<Nothing>> get() = mutableProgressLiveData

    fun searchImagesByText(text: String) {
        if (text.isEmpty()){
            mutableMessageLiveData.value = Result.Message(resourceUtil.getString(R.string.error_empty_text))
            return
        }
        currentPage = 1
        currentSearchText = text
        loadImages(currentSearchText, currentPage)
    }

    fun loadNextPage() {
        currentPage++
        loadImages(currentSearchText, currentPage)
    }

    private fun loadImages(text: String, page: Int) {
        mutableProgressLiveData.value = Result.Loading
        pixabayDataSource.getImagesByText(text, page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ pixabayImageList ->
                onImagesReceived(pixabayImageList)
            }, { throwable ->
                onErrorReceived(throwable)
            })
            .addTo(compositeDisposable)
    }

    private fun onErrorReceived(throwable: Throwable) {
        mutableErrorLiveData.value = Result.Error(throwable)
    }

    private fun onImagesReceived(pixabayImageList: PixabayImagesList) {
        if (pixabayImageList.hits.isEmpty()) {
            mutableMessageLiveData.value = Result.Message(resourceUtil.getString(R.string.error_no_results))
        } else {
            mutableImagesLiveData.value = Result.Success(pixabayImageList.hits)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}