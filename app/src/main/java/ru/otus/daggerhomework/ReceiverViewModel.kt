package ru.otus.daggerhomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReceiverViewModel(
    private val sharedFlow: SharedFlow<Int>
) : ViewModel() {
    fun observeColors(viewModelReceiver: ViewModelReceiver, populate: (Int) -> Unit) {
        viewModelScope.launch {
            sharedFlow.collect {
                viewModelReceiver.observeColors(it, populate)
            }
        }
    }
}

class ReceiverViewModelFactory @Inject constructor(
    private val sharedFlow: SharedFlow<Int>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceiverViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReceiverViewModel(
                sharedFlow = sharedFlow
            ) as T
        } else throw IllegalArgumentException()
    }
}