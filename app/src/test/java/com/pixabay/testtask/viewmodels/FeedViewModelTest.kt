package com.pixabay.testtask.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pixabay.testtask.TestData
import com.pixabay.testtask.data.datasource.PixabayDataSource
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.ui.feed.FeedViewModel
import com.pixabay.testtask.util.ResourceUtil
import com.pixabay.testtask.util.Result
import com.pixabay.testtask.util.SchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var schedulerProvider: SchedulerProvider
    @Mock
    lateinit var pixabayDataSource: PixabayDataSource
    @Mock
    lateinit var resourceUtil: ResourceUtil
    @Mock
    lateinit var observer: Observer<Result<List<PixabayImage>>>

    lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        feedViewModel = FeedViewModel(schedulerProvider, pixabayDataSource, resourceUtil)
        `when`(resourceUtil.getString(ArgumentMatchers.anyInt())).thenAnswer { "Test" }
        `when`(schedulerProvider.io()).thenAnswer { Schedulers.trampoline()}
        `when`(schedulerProvider.ui()).thenAnswer { Schedulers.trampoline()}
        feedViewModel.imagesLiveData.observeForever(observer)
    }

    @Test
    fun fetchImagesWithResponse() {
        `when`(this.pixabayDataSource.getImagesByText(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
            .thenAnswer {
                return@thenAnswer Single.just(TestData.getTestData())
            }
        feedViewModel.searchImagesByText("Test")
        assertNotNull(feedViewModel.progressLiveData.value)
        assertNotNull(feedViewModel.imagesLiveData.value)
        assertEquals(feedViewModel.progressLiveData.value, feedViewModel.progressLiveData.value as Result.Loading)
        assertEquals(feedViewModel.imagesLiveData.value, feedViewModel.imagesLiveData.value as Result.Success)
    }

    @Test
    fun fetchImagesWithErrorResponse() {
        `when`(this.pixabayDataSource.getImagesByText(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
            .thenAnswer {
                return@thenAnswer Single.error<Throwable>(Throwable())
            }
        feedViewModel.imagesLiveData.observeForever(observer)
        feedViewModel.searchImagesByText("Test")
        assertNotNull(feedViewModel.progressLiveData.value)
        assertNotNull(feedViewModel.errorLiveData.value)
        assertEquals(feedViewModel.progressLiveData.value, feedViewModel.progressLiveData.value as Result.Loading)
        assertEquals(feedViewModel.errorLiveData.value, feedViewModel.errorLiveData.value as Result.Error)
    }

    @Test
    fun launchFetchingWithEmptySearchText() {
        feedViewModel.imagesLiveData.observeForever(observer)
        feedViewModel.searchImagesByText(ArgumentMatchers.anyString())
        assertNotNull(feedViewModel.messageLiveData.value)
        assertNull(feedViewModel.imagesLiveData.value)
        assertNull(feedViewModel.errorLiveData.value)
        assertNull(feedViewModel.progressLiveData.value)
    }

    @Test
    fun fetchingWithNextPageLoading(){
        `when`(this.pixabayDataSource.getImagesByText(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
            .thenAnswer {
                return@thenAnswer Single.just(TestData.getTestData())
            }
        val spiedViewModel = spy(feedViewModel)
        spiedViewModel.searchImagesByText("Test")
        spiedViewModel.loadNextPage()
        verify(spiedViewModel, times(1)).searchImagesByText("Test")
    }
}