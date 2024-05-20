package ru.otus.daggerhomework.di.fragments.receiver

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.otus.daggerhomework.ReceiverViewModelFactory

@Module
interface FragmentReceiverModule {
    @Binds
    fun provideReceiverViewModelFactory(
        receiverViewModelFactory: ReceiverViewModelFactory
    ): ViewModelProvider.Factory
}