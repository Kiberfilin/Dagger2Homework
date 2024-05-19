package ru.otus.daggerhomework.di.application

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.otus.daggerhomework.di.annotation.qualifier.Application
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    @Application
    fun provideApplicationContext(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Application appContext: Context): ApplicationComponent
    }

    companion object {
        fun createApplicationComponent(context: Context): ApplicationComponent =
            DaggerApplicationComponent.factory().create(context)
    }
}