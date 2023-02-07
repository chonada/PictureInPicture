package com.example.android.pictureinpicture.di

import androidx.transition.Visibility.Mode
import com.example.android.pictureinpicture.InMemoryTimerSessionDataSourceImpl
import com.example.android.pictureinpicture.TimerSessionDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class TimerSessionDataSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun provideTimerSessionDataSource(
        timerSessionDataSourceImpl: InMemoryTimerSessionDataSourceImpl
    ) : TimerSessionDataSource
}