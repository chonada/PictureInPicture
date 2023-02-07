package com.example.android.pictureinpicture.util

import android.os.SystemClock
import kotlinx.coroutines.android.awaitFrame
import javax.inject.Inject

interface TimeSource {
    fun getTimeMillis(): Long
    suspend fun delay(): Long
}

class SystemTimeSource @Inject constructor() : TimeSource {
    override fun getTimeMillis() = SystemClock.uptimeMillis()
    override suspend fun delay() = awaitFrame()
}