package com.example.android.pictureinpicture

class StoredTimeRepository(private val storedTimesDataSource: StoredTimeDataSource = InMemoryStoredTimeDataSourceImpl()) {
    var timeStamp: Long
    get() = storedTimesDataSource.readStoredTimeMillis()
    set(value) = storedTimesDataSource.storeTimeMillis(value)
}