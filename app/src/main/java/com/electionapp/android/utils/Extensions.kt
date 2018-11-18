package com.electionapp.android.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.ColorUtils
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.electionapp.android.ApplicationClass
import com.electionapp.android.R
import com.electionapp.android.di.AppComponent
import java.util.*
import android.view.WindowManager
import android.util.TypedValue
import android.text.StaticLayout
import android.text.Layout
import android.text.TextPaint


/**
 * Created by aliumujib on 09/06/2018.
 */

inline fun Activity.dpToPx(dp: Int): Int {
    var displayMetrics = resources.displayMetrics
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}

inline fun Context.dpToPx(dp: Int): Int {
    var displayMetrics = resources.displayMetrics
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}

fun Context.getHeightForText(text: String, textSize: Int): Float {
    val myTextPaint = TextPaint()
    myTextPaint.isAntiAlias = true
    myTextPaint.textSize = textSize * resources.displayMetrics.density
    myTextPaint.color = -0x1000000

    val width = 200
    val alignment = Layout.Alignment.ALIGN_NORMAL
    val spacingMultiplier = 1f
    val spacingAddition = 0f
    val includePadding = false

    val myStaticLayout = StaticLayout(text, myTextPaint, width, alignment, spacingMultiplier, spacingAddition, includePadding)

    return myStaticLayout.height.toFloat()
}

fun Context.getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}


fun Context.getColorHexString(@ColorRes resId: Int): String {
    val colorInt = ContextCompat.getColor(this, resId)
    return String.format("#%06X", 0xFFFFFF and colorInt)
}

fun Context.getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun <E> List<E>.random(random: java.util.Random): E? = if (size > 0) get(random.nextInt(size)) else null

fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null

fun Activity.appComponent(): AppComponent = ApplicationClass.getInstance().appComponent

fun Fragment.appComponent(): AppComponent = ApplicationClass.getInstance().appComponent

fun Activity.setStatusBarTranslucent(makeTranslucent: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        if (makeTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}

fun ActionBar.setEmptyTitle() {
    this.title = ""
}


fun Toolbar.addStatusBarPadding() {
    var paddingTop = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) context.getStatusBarHeight() else 0
    val tv = TypedValue()
    this.context.theme.resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true)
    paddingTop += TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    this.setPadding(0, paddingTop, 0, 0)
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

inline fun Fragment.dpToPx(dp: Int): Int = activity!!.dpToPx(dp)

fun Fragment.finish() {
    activity?.finish()
}

fun Activity.showLoading() {
    findViewById<View>(R.id.loader_view).visibility = View.VISIBLE
}

fun Toolbar.setEmptyTitle() {
    title = ""
}

fun Activity.hideLoading() {
    findViewById<View>(R.id.loader_view).visibility = View.GONE
}

fun Fragment.hideViewLoading() {
    activity?.hideLoading()
}

fun Fragment.showViewLoading() {
    activity?.showLoading()
}

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Fragment.appCompatActivity(): AppCompatActivity {
    return activity as AppCompatActivity
}

fun String.toCamelCase(): String {
    val parts = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    var camelCaseString = ""
    parts.forEachIndexed { index, part ->
        camelCaseString += if (index == 0) {
            part.capitalize()
        } else {
            " ${part.capitalize()}"
        }
    }
    return camelCaseString
}

fun Fragment.hideKeyBoard() {
    val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
}

fun manipulateColor(color: Int, factor: Float): Int {
    val a = Color.alpha(color)
    val r = Math.round(Color.red(color) * factor)
    val g = Math.round(Color.green(color) * factor)
    val b = Math.round(Color.blue(color) * factor)
    return Color.argb(a,
            Math.min(r, 255),
            Math.min(g, 255),
            Math.min(b, 255))
}

inline val @receiver:ColorInt Int.darken
    @ColorInt
    get() = ColorUtils.blendARGB(this, Color.BLACK, 0.2f)

//TAG in the companion object for fragments  exists because https://discuss.kotlinlang.org/t/static-extension-methods-for-java-classes/2190
fun Fragment.TAG(): String = javaClass.canonicalName