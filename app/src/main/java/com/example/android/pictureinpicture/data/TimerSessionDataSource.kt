package com.example.android.pictureinpicture

import com.example.android.pictureinpicture.data.TimerSession
import javax.inject.Inject

/**
 * The interface defining the operations supported by the data source
 *
 */
interface TimerSessionDataSource {
    fun readTimerSession(): TimerSession
    fun storeTimerSession(timerSession: TimerSession)
    fun clearTimerSession()
}

class InMemoryTimerSessionDataSourceImpl @Inject constructor(): TimerSessionDataSource {
    override fun readTimerSession(): TimerSession = timerSession

    override fun storeTimerSession(timerSession: TimerSession) {
        Store.timerSession = timerSession
    }

    override fun clearTimerSession() {
        timerSession = TimerSession(0L, 0L,false)
    }

    companion object Store {
        private var timerSession: TimerSession = TimerSession(0L, 0L,false)
    }
}