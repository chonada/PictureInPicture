package com.example.android.pictureinpicture.di

import com.example.android.pictureinpicture.util.SystemTimeSource
import com.example.android.pictureinpicture.util.TimeSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class TimeSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun providesTimeSource(
        timeSource: SystemTimeSource
    ): TimeSource
}