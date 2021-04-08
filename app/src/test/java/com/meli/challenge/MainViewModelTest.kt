package com.meli.challenge

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.meli.challenge.api.ApiRepository
import com.meli.challenge.api.SearchService
import com.meli.challenge.models.Product
import com.meli.challenge.models.Results
import com.meli.challenge.utils.Resource
import com.meli.challenge.viewmodels.MainViewModel
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var apiHelper = mock(ApiRepository::class.java)

    private val saveState = mockk<SavedStateHandle>(relaxed = true)

    @InjectMocks
    var mainViewModel = MainViewModel(saveState,apiHelper, Application())

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<Results>>

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(saveState,apiHelper, Application())
    }

    /*@Test
    fun testSomething() {
        testCoroutineRule.runBlockingTest {
            println("Called doSomething")
                apiHelper.getSearch("query")
        }
        println("Getting result value")
        val result = mainViewModel.response.value
        println("Result value : $result")
        assertNotNull(result) // Fails here
    }*/

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Product>())
                .`when`(apiHelper)
                .getSearch("")
            val viewModel = MainViewModel(saveState, apiHelper, Application())
            viewModel.response.observeForever(apiUsersObserver)
            verify(apiHelper).getSearch("")
            verify(apiUsersObserver).onChanged(Resource.success(Results(listOf())))
            viewModel.response.removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getSearch("")
            val viewModel = MainViewModel(saveState, apiHelper, Application())
            viewModel.response.observeForever(apiUsersObserver)
            verify(apiHelper).getSearch("")
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.response.removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}