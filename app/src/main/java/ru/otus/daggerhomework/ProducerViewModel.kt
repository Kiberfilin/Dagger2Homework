package ru.otus.daggerhomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProducerViewModel(
    private val mutableSharedFlow: MutableSharedFlow<Int>
) : ViewModel() {
    fun onButtonClick(viewModelProducer: ViewModelProducer) {
        viewModelScope.launch {
            mutableSharedFlow.emit(
                viewModelProducer.generateColor()
            )
        }
    }
}

class ProducerViewModelFactory @Inject constructor(
    private val mutableSharedFlow: MutableSharedFlow<Int>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProducerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProducerViewModel(
                mutableSharedFlow = mutableSharedFlow
            ) as T
        } else throw IllegalArgumentException()
    }
}