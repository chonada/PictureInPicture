package com.example.android.pictureinpicture.data

import com.example.android.pictureinpicture.InMemoryTimerSessionDataSourceImpl
import com.example.android.pictureinpicture.TimerSessionDataSource
import javax.inject.Inject

class TimerSessionRepository @Inject constructor(
    private val timerSessionDataSource: TimerSessionDataSource
) {
    var timerSession: TimerSession
    get() = timerSessionDataSource.readTimerSession()
    set(value) = timerSessionDataSource.storeTimerSession(value)
    fun clear() = timerSessionDataSource.clearTimerSession()
}