package com.example.android.pictureinpicture.di

import com.example.android.pictureinpicture.util.SystemTimeSource
import com.example.android.pictureinpicture.util.TimeSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeSourceModule {
    @Binds
    abstract fun providesTimeSource(
        timeSource: SystemTimeSource
    ): TimeSource
}