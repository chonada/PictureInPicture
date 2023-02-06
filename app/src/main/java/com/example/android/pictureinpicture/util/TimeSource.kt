package com.example.android.pictureinpicture.util

import android.os.SystemClock
import kotlinx.coroutines.android.awaitFrame

interface TimeSource {
    fun getTimeMillis(): Long
    suspend fun delay(): Long
}

class SystemTimeSource() : TimeSource {
    override fun getTimeMillis() = SystemClock.uptimeMillis()
    override suspend fun delay() = awaitFrame()
}