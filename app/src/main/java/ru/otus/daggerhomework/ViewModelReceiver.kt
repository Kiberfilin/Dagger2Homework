package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.otus.daggerhomework.di.annotation.qualifier.Application
import javax.inject.Inject

class ViewModelReceiver @Inject constructor(
    private val context: Context,
    private val sharedFlow: SharedFlow<Int>
) : ViewModel() {

    fun observeColors(populateAction: (Int) -> Unit) {
        viewModelScope.launch {
            sharedFlow.collect {
                if (context !is android.app.Application) throw RuntimeException("Здесь нужен контекст апликейшена")
                Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
                populateAction(it)
            }
        }
    }
}

class ReceiverViewModelFactory @Inject constructor(
    @Application
    private val context: Context,
    private val sharedFlow: SharedFlow<Int>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelReceiver::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelReceiver(
                context = context,
                sharedFlow = sharedFlow
            ) as T
        } else throw IllegalArgumentException()
    }
}