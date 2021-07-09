package com.tousif.webskitter.utilities.extensons

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.util.Patterns
import androidx.annotation.IntRange
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.tousif.webskitter.helper_classes.BaseViewModelFactory
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sign


inline fun trycatch(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Date?.convertToString(
    outputFormat: String = "HH:mm:ss",
    localeId: Locale = Locale.getDefault()
): String {
    if (this != null) {
        val requiredFormat = SimpleDateFormat(outputFormat, localeId)
        try {
            return requiredFormat.format(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return ""
}

fun Date?.format(
    outputFormat: String = "yyyy-MM-dd HH:mm:ss",
    localeId: Locale = Locale.getDefault()
): Date? {
    var cdate: Date? = null
    if (this != null) {
        val requiredFormat = SimpleDateFormat(outputFormat, localeId)
        try {
            cdate = requiredFormat.parse(requiredFormat.format(this))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return cdate
}

fun String.convertToDate(format: String = "yyyy-MM-dd", localeId: Locale = Locale.getDefault()): Date? {
    try {
        return SimpleDateFormat(format, localeId).parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun Long.convertToDateTime(): Date? {
    try {
        val date = Date(this)
        val requiredFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return requiredFormat.parse(requiredFormat.format(date))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun Boolean.convertToInt(): Int {
    return if (this) 1 else 0
}

fun String.isNotNull(): Boolean {
    return this != "null"
}

fun Float.isNegative(): Boolean {
    return this < 0
}

fun Float.toDecimal(): Float {
    /*val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.UP
    return df.format(this).toFloat()*/
    /*val factor = 10.0.pow(2.toDouble())
    return ((this * factor).roundToInt() / factor).toFloat()*/
    return "%.${2}f".format(Locale.ENGLISH, this).toFloat()
}

fun Float.toBigDecimal(): Float {
    return "%.${5}f".format(Locale.ENGLISH, this).toFloat()
}

fun Float.toCeiling(): Float {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toFloat()
}

fun Activity.hasPermission(vararg permissions: String): Boolean {
    return permissions.all { singlePermission ->
        applicationContext.checkSelfPermission(singlePermission) == PackageManager.PERMISSION_GRANTED
    }
}

fun Activity.askPermission(vararg permissions: String, @IntRange(from = 0) requestCode: Int) =
    ActivityCompat.requestPermissions(this, permissions, requestCode)

fun Activity.isUserCheckNeverAskAgain(vararg permissions: String): Boolean {
    return permissions.all { singlePermission ->
        !ActivityCompat.shouldShowRequestPermissionRationale(this, singlePermission)
    }
}

/**Create Factory Class at runtime*/
inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, BaseViewModelFactory(creator)).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, BaseViewModelFactory(creator)).get(T::class.java)
}

/**Validate String Email*/
fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

/** Log functions with Tag as caller class name */
inline fun <reified T : Any> T.logD(text: String) {
    Log.d(T::class.java.simpleName, "TISHA ===> $text")
}

/** Log functions with Tag as caller class name */
inline fun <reified T : Any> T.loge(text: String) {
    Log.d(T::class.java.simpleName, "ERROR ===> $text")
}

/** Log functions with Tag as caller class name */
inline fun <reified T : Any> T.logj(list: MutableList<T>) {
    Log.d(T::class.java.simpleName, "TISHA ===> ${Gson().toJson(list)}")
}

/**Float To String*/
val Float.counterToString
    get() = if (this > toInt()) toString() else toInt().toString()

/**Float Conversion*/
val Float.conversion: String
    get() {
        return when (sign) {
            1.0F -> {
                /*Positive*/
                if (this > toInt()) toString() else toInt().toString()
            }
            -1.0F -> {
                /*Negative*/
                if (this < toInt()) toString() else toInt().toString()
            }
            0F -> {
                /*Zero*/
                toInt().toString()
            }
            else -> "?"
        }
    }

/**@return `true` if called on the intro thread, `false` otherwise.*/
val isOnMainThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

/**@return `true` if called on the background thread, `false` otherwise.*/
val isOnBackgroundThread: Boolean
    get() = !isOnMainThread

/**@return the current Thread.*/
val currentThread: Thread
    get() = Thread.currentThread()


/**@return "" to create an empty string*/
@Suppress("NOTHING_TO_INLINE")
internal inline fun emptyString() = ""
