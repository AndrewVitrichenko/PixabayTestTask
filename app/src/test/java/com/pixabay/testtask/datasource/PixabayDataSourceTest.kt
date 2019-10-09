package com.pixabay.testtask.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pixabay.testtask.data.datasource.PixabayDataSource
import com.pixabay.testtask.data.datasource.PixabayRemoteDataSource
import com.pixabay.testtask.data.datasource.PixabayRepository
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito
import com.pixabay.testtask.TestData
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers

@RunWith(MockitoJUnitRunner::class)
class PixabayDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var pixabayRemoteDataSource: PixabayRemoteDataSource

    private lateinit var pixabayDataSource: PixabayDataSource

    @Before
    fun setup() {
        pixabayDataSource = PixabayRepository(pixabayRemoteDataSource)
           }

    @Test
    fun testFetchingWithSuccess(){
        Mockito.`when`(
            this.pixabayDataSource.getImagesByText(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        )
            .thenAnswer {
                return@thenAnswer Single.just(TestData.getTestData())
            }
        val testObserver = pixabayDataSource.getImagesByText("Test",0).test()
        testObserver.assertValues(TestData.getTestData())
    }

    @Test
    fun testFetchingWithError(){
        val throwable = Throwable()
        Mockito.`when`(
            this.pixabayDataSource.getImagesByText(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        )
            .thenAnswer {
                return@thenAnswer Single.error<Throwable>(throwable)
            }
        val testObserver = pixabayDataSource.getImagesByText("Test",0).test()
        testObserver.assertError(throwable)
    }


}