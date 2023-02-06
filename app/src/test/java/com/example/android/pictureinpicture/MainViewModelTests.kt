package com.example.android.pictureinpicture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.pictureinpicture.data.TimerSessionRepository
import com.example.android.pictureinpicture.util.TimeSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTests {

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dataSource = InMemoryTimerSessionDataSourceImpl()
    private var repository = TimerSessionRepository(dataSource)
    private var testTimeSource = object : TimeSource {
        var uptimeMillis = 0L
        val frameDelay = 100L
        override fun getTimeMillis() = uptimeMillis++
        override suspend fun delay(): Long {
            kotlinx.coroutines.delay(frameDelay)
            return frameDelay
        }
    }

    @Test
    fun test_stopwatch_initial_launch() {
        val viewModel = MainViewModel(repository, testTimeSource)
        viewModel.startOrPause()
        viewModel.time.observeForever {}
        mainCoroutineRule.scheduler.advanceTimeBy(2000)
        assertEquals(viewModel.time.value, "00:00:02")
    }

    @Test
    fun test_relaunch_of_stopwatch() {
        dataSource.clearTimerSession()
        var viewModel = MainViewModel(repository, testTimeSource)
        viewModel.startOrPause()
        viewModel.time.observeForever {}
        mainCoroutineRule.scheduler.advanceTimeBy(1000)
        assertEquals(viewModel.time.value, "00:00:01")

        val dataSource1 = InMemoryTimerSessionDataSourceImpl()
        val repository1 = TimerSessionRepository(dataSource1)
        viewModel = MainViewModel(repository1, testTimeSource)
        viewModel.startOrPause()
        viewModel.time.observeForever {}
        mainCoroutineRule.scheduler.advanceTimeBy(10000)
        assertEquals(viewModel.time.value, "00:00:19")
    }

}