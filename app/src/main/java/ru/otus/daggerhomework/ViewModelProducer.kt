package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.otus.daggerhomework.di.annotation.qualifier.Activity
import javax.inject.Inject

class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,
    private val context: Context,
    private val mutableSharedFlow: MutableSharedFlow<Int>
) : ViewModel() {

    fun generateColor() {
        viewModelScope.launch {
            mutableSharedFlow.emit(colorGenerator.generateColor())
            if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
            Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        }
    }
}

class ProducerViewModelFactory @Inject constructor(
    @Activity
    private val context: Context,
    private val colorGenerator: ColorGenerator,
    private val mutableSharedFlow: MutableSharedFlow<Int>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelProducer::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelProducer(
                colorGenerator = colorGenerator,
                context = context,
                mutableSharedFlow = mutableSharedFlow
            ) as T
        } else throw IllegalArgumentException()
    }
}