package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class ViewModelReceiver @Inject constructor(
    @ru.otus.daggerhomework.di.annotation.qualifier.Application
    private val context: Context,
    private val sharedFlow: SharedFlow<Int>
) {

    suspend fun observeColors(populate: (Int) -> Unit) {
        sharedFlow.collect { color: Int ->
            if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
            Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
            populate(color)
        }
    }
}
