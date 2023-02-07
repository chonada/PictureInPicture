package com.example.android.pictureinpicture.di

import androidx.transition.Visibility.Mode
import com.example.android.pictureinpicture.InMemoryTimerSessionDataSourceImpl
import com.example.android.pictureinpicture.TimerSessionDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimerSessionDataSourceModule {
    @Binds
    abstract fun provideTimerSessionDataSource(
        timerSessionDataSourceImpl: InMemoryTimerSessionDataSourceImpl
    ) : TimerSessionDataSource
}