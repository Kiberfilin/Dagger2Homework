package ru.otus.daggerhomework.di.fragments.producer

import dagger.Component
import ru.otus.daggerhomework.FragmentProducer
import ru.otus.daggerhomework.di.annotation.scope.FragmentScope
import ru.otus.daggerhomework.di.mainactivity.MainActivityComponent

@FragmentScope
@Component(
    dependencies = [MainActivityComponent::class],
    modules = [FragmentProducerModule::class]
)
interface FragmentProducerComponent {

    fun inject(fragmentProducer: FragmentProducer)

    companion object {
        fun create(mainActivityComponent: MainActivityComponent) =
            DaggerFragmentProducerComponent.builder().mainActivityComponent(mainActivityComponent)
                .build()
    }
}