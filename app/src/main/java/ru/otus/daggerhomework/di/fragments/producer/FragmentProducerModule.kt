package ru.otus.daggerhomework.di.fragments.producer

import dagger.Binds
import dagger.Module
import ru.otus.daggerhomework.ColorGenerator
import ru.otus.daggerhomework.ColorGeneratorImpl

@Module
interface FragmentProducerModule {
    @Binds
    fun provideColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator
}