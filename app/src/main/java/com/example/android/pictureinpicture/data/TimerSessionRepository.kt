package com.example.android.pictureinpicture.data

import com.example.android.pictureinpicture.InMemoryTimerSessionDataSourceImpl
import com.example.android.pictureinpicture.TimerSessionDataSource

class TimerSessionRepository(private val timerSessionDataSource: TimerSessionDataSource = InMemoryTimerSessionDataSourceImpl()) {
    var timerSession: TimerSession
    get() = timerSessionDataSource.readTimerSession()
    set(value) = timerSessionDataSource.storeTimerSession(value)
    fun clear() = timerSessionDataSource.clearTimerSession()
}