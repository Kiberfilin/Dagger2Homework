package ru.otus.daggerhomework

import android.app.Application

class App : Application() {
    var applicationComponent: ApplicationComponent? = null
        private set
        get() = field ?: ApplicationComponent.createApplicationComponent(this)
            .also { field = it }
}