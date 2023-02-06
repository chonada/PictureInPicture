package com.example.android.pictureinpicture.data

import com.example.android.pictureinpicture.widget.MovieView

/**
 * Data for a session that is saved.
 * @property timestamp The system uptime when
 * @property offset The time in millis that the timer was running for.
 * @property started Boolean representing the timer was running or paused.
 */
data class TimerSession(
    val timestamp: Long,
    val offset: Long,
    val started: Boolean
)
