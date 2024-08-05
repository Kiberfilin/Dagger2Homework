package ru.otus.daggerhomework.di.fragments.receiver

import dagger.Component
import ru.otus.daggerhomework.FragmentReceiver
import ru.otus.daggerhomework.di.annotation.scope.FragmentScope
import ru.otus.daggerhomework.di.mainactivity.MainActivityComponent

@FragmentScope
@Component(
    dependencies = [MainActivityComponent::class]
)
interface FragmentReceiverComponent {

    fun inject(fragmentReceiver: FragmentReceiver)

    companion object {
        fun create(mainActivityComponent: MainActivityComponent) =
            DaggerFragmentReceiverComponent.builder().mainActivityComponent(mainActivityComponent)
                .build()
    }
}