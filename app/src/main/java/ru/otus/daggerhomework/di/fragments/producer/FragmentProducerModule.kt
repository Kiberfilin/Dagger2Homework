package ru.otus.daggerhomework.di.fragments.producer

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.otus.daggerhomework.ColorGenerator
import ru.otus.daggerhomework.ColorGeneratorImpl
import ru.otus.daggerhomework.ProducerViewModelFactory

@Module
interface FragmentProducerModule {
    @Binds
    fun provideColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator

    @Binds
    fun provideProducerViewModelFactory(
        producerViewModelFactory: ProducerViewModelFactory
    ): ViewModelProvider.Factory
}