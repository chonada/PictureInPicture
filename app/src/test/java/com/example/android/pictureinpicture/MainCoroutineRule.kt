package com.example.android.pictureinpicture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule() :
    TestWatcher() {
    lateinit var scheduler: TestCoroutineScheduler

    lateinit var dispatcher: TestDispatcher

    override fun starting(description: Description) {
        super.starting(description)
        scheduler = TestCoroutineScheduler()
        dispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}