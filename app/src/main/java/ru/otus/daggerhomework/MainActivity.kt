package ru.otus.daggerhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.otus.daggerhomework.di.mainactivity.MainActivityComponent

class MainActivity : AppCompatActivity() {

    private val colorEmitter: MutableSharedFlow<Int> = MutableSharedFlow()
    var mainActivityComponent: MainActivityComponent? = null
        private set
        get() = field ?: MainActivityComponent.createMainActivityComponent(
            this,
            colorEmitter,
            (application as App).applicationComponent!!
        ).also { field = it }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerForProducer, FragmentProducer())
            .add(R.id.containerForReceiver, FragmentReceiver())
            .commit()
    }
}