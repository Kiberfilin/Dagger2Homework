package ru.otus.daggerhomework.di.mainactivity

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.otus.daggerhomework.di.annotation.qualifier.Activity
import ru.otus.daggerhomework.di.annotation.qualifier.Application
import ru.otus.daggerhomework.di.annotation.scope.ActivityScope
import ru.otus.daggerhomework.di.application.ApplicationComponent

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface MainActivityComponent {
    @Activity
    fun provideActivityContext(): Context

    @Application
    fun provideApplicationContext(): Context
    fun provideFlowForProduceColors(): MutableSharedFlow<Int>
    fun provideFlowForReceiveColors(): SharedFlow<Int>

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @Activity activityContext: Context,
            @BindsInstance producerFlow: MutableSharedFlow<Int>,
            @BindsInstance receiverFlow: SharedFlow<Int>,
            applicationComponent: ApplicationComponent
        ): MainActivityComponent
    }

    companion object {
        fun createMainActivityComponent(
            context: Context,
            producerFlow: MutableSharedFlow<Int>,
            receiverFlow: SharedFlow<Int>,
            applicationComponent: ApplicationComponent
        ): MainActivityComponent =
            DaggerMainActivityComponent.factory()
                .create(context, producerFlow, receiverFlow, applicationComponent)
    }
}