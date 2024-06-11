package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentActivity
import ru.otus.daggerhomework.di.annotation.qualifier.Activity
import javax.inject.Inject

class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,
    @Activity
    private val context: Context
) {
    @ColorInt
    fun generateColor(): Int {
        if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        return colorGenerator.generateColor()
    }
}
