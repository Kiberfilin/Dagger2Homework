package ru.otus.daggerhomework.di.mainactivity

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Module
class MainActivityModule(
    private val colorEmitter: MutableSharedFlow<Int>
) {
    @Provides
    fun provideProducerFlow(): MutableSharedFlow<Int> = colorEmitter

    @Provides
    fun provideReceiverFlow(): SharedFlow<Int> = colorEmitter.asSharedFlow()
}