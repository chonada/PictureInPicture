/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.pictureinpicture

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.android.pictureinpicture.data.TimerSessionRepository
import com.example.android.pictureinpicture.data.TimerSession
import com.example.android.pictureinpicture.util.SystemTimeSource
import com.example.android.pictureinpicture.util.TimeSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel(
    private val timerSessionRepository: TimerSessionRepository,
    private val timeSource: TimeSource
    ): ViewModel() {
    private var job: Job? = null

    private var startUptimeMillis = timeSource.getTimeMillis()
    private val timeMillis = MutableLiveData(initialOffset)

    // The timeoffset that is set initially. This will be zero when starting afresh,
    // but when restarting after coming back from MovieActivity,
    // this will be calculated as a sum of previous timeoffset (when leaving MainActivity) plus the
    // milliseconds that the MainActivity was  dead for.
    private val initialOffset: Long
    get() {
        return if (previouslyStarted) {
            val timeSpent =
                timeSource.getTimeMillis() - timerSessionRepository.timerSession.timestamp
            timerSessionRepository.timerSession.offset + timeSpent
        } else {
            timerSessionRepository.timerSession.offset
        }
    }

    // Flag representing if the stopwatch was running when MainActivity exited
    private val previouslyStarted: Boolean
    get() = timerSessionRepository.timerSession.started

    private val _started = MutableLiveData(previouslyStarted)

    val started: LiveData<Boolean> = _started

    // Converts the timestamp to a displayable string
    val time = timeMillis.map { millis ->
        val minutes = millis / 1000 / 60
        val m = minutes.toString().padStart(2, '0')
        val seconds = (millis / 1000) % 60
        val s = seconds.toString().padStart(2, '0')
        val hundredths = (millis % 1000) / 10
        val h = hundredths.toString().padStart(2, '0')
        "$m:$s:$h"
    }

    /**
     * Starts the stopwatch if it is not yet started, or pauses it if it is already started.
     */
    fun startOrPause() {
        if (_started.value == true) {
            pause()
        } else {
            start()
        }
    }

    /**
     * Starts the stopwatch.
     */
    private fun start() {
        _started.value = true
        job = viewModelScope.launch { start() }
        job?.invokeOnCompletion {
            val offset = timeMillis.value ?: 0L
            val timeStamp = SystemClock.uptimeMillis()
            val state = started.value ?: false
            timerSessionRepository.timerSession = TimerSession(timeStamp, offset, state)
        }
    }

    /**
     * Pause the stopwatch.
     */
    private fun pause() {
        _started.value = false
        job?.cancel()
    }

    /**
     * If stopwatch was running previously when the activity exited,
     * then start again.
     */
    fun evaluateAndRestoreState() {
        if (previouslyStarted) {
            start()
        }
    }

    private suspend fun CoroutineScope.start() {
        startUptimeMillis = timeSource.getTimeMillis() - (timeMillis.value ?: 0L)
        while (isActive) {
            timeMillis.value = timeSource.getTimeMillis() - startUptimeMillis
            // Updates on every render frame.
            timeSource.delay()
        }
    }

    /**
     * Clears the stopwatch to 00:00:00.
     * Cleanup the repository
     */
    fun clear() {
        startUptimeMillis = timeSource.getTimeMillis()
        timeMillis.value = 0L
        timerSessionRepository.clear()
    }
    companion object {
        /**
         * ViewModel Factory
         */
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return MainViewModel(TimerSessionRepository(), SystemTimeSource()) as T
            }
        }
    }
}
