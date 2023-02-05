package com.example.android.pictureinpicture

interface StoredTimeDataSource {
    fun readStoredTimeMillis(): Long
    fun storeTimeMillis(time: Long)
    fun clearStoredTime()
}

class InMemoryStoredTimeDataSourceImpl(): StoredTimeDataSource {
    override fun readStoredTimeMillis(): Long = timeStore

    override fun storeTimeMillis(time: Long) {
        timeStore = time
    }

    override fun clearStoredTime() {
        timeStore = 0L
    }

    companion object {
        private var timeStore: Long = 0L
    }
}