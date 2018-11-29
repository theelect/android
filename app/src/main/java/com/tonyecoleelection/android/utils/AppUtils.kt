package com.tonyecoleelection.android.utils

import android.content.Context
import android.os.Handler
import android.support.annotation.StringRes
import android.widget.Toast
import java.util.*

/*
 * Created by troy379 on 04.04.17.
 */
object AppUtils {

    fun showToast(context: Context, @StringRes text: Int, isLong: Boolean) {
        showToast(context, context.getString(text), isLong)
    }

    fun showToast(context: Context, text: String, isLong: Boolean) {
        Toast.makeText(context, text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }
}

fun Any.delay(function: () -> Unit, timeMillis: Long = 1000) {
    val handler = Handler()
    handler.postDelayed({
        function.invoke()
    }, timeMillis)
}

fun Any.delayForASecond(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed({
        function.invoke()
    }, 1000)
}

fun <E> List<E>.randomElements(number: Int): List<E> {
    val copy: List<E> = this
    Collections.shuffle(copy)
    return copy.subList(0, number)
}

