package com.tousif.webskitter.utilities.extensons

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tousif.webskitter.R


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toast(message: String, gravity: Int) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.setGravity(gravity, 0, 0)
    toast.show()
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this.requireContext(), msg, duration).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.toggleVisibility() {
    visibility = if (visibility == View.GONE) View.VISIBLE else View.GONE
}

/**View Visibility Ext*/
fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

/**View Enable/Disable Ext*/
fun View.enable() {
    isEnabled = true
    alpha = 1f
}

fun View.disable() {
    isEnabled = false
    alpha = 0.5f
}

fun View.clickEffect() {
    AnimationUtils.loadAnimation(context, R.anim.anim_click_effect)?.let {
        startAnimation(it)
    }
}

/**View Inflation Ext*/
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**View Get Value Ext*/
val Button.value
    get() = text?.toString() ?: ""

val EditText.value
    get() = text?.toString() ?: ""

val TextView.value
    get() = text?.toString() ?: ""

/**View Empty validation Ext*/
fun Button.isEmpty() = value.isEmpty()

fun EditText.isEmpty() = value.isEmpty()

fun TextView.isEmpty() = value.isEmpty()

/**Get resIdByName Ext*/
fun Context.resIdByName(resIdName: String?, resType: String): Int {
    resIdName?.let {
        return resources.getIdentifier(it, resType, packageName)
    }
    throw Resources.NotFoundException()
}

/**Get String resource value by resIdByName Ext*/
fun Context.getStringValue(resIdName: String): String {
    return resources.getString(resIdByName(resIdName, "string"))
}

/**Get Color resource value by resIdByName Ext*/
/*fun Context.getColorValue(resIdName: String, theme: Resources.Theme? = null): Int {
    return resources.getColor(resIdByName(resIdName, "color"), theme)
}*/

/**Get child Views from ViewGroup*/
val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

operator fun ViewGroup.get(index: Int): View? = getChildAt(index)

//======/ ImageView /============================================
/*fun ImageView.loadImage(byteArray: ByteArray?) {
    Glide.with(this)
        .asBitmap()
        .load(byteArray)
        .placeholder(R.color.GhostWhite)
        .into(this)
}

fun ImageView.loadImage(imageId: Int) {
    Glide.with(this)
        .asBitmap()
        .load(imageId)
        .placeholder(R.color.GhostWhite)
        .into(this)
}

fun ImageView.loadGif(imageId: Int) {
    Glide.with(this)
        .asGif()
        .load(imageId)
        .into(this)
}*/

//============================/ ImageView /======
/**deflate ViewStub from ViewGroup*/
fun ViewStub.deflate(view: View): ViewStub {
    val viewParent = view.parent

    if (viewParent != null && viewParent is ViewGroup) {
        val index = viewParent.indexOfChild(view)
        viewParent.removeView(view)
        val viewStub = ViewStub(context).apply {
            inflatedId = this@deflate.inflatedId
            layoutParams = this@deflate.layoutParams
        }
        viewParent.addView(viewStub, index)
        return viewStub
    } else {
        throw IllegalStateException("Inflated View has not a parent")
    }
}

/**Extension method for blinkingView.*/
fun View.blinkingView(tracking: Boolean) {
    val anim = AlphaAnimation(0.0f, 1.0f)
    anim.duration = 300 //You can manage the blinking time with this
    anim.startOffset = 20
    anim.repeatMode = Animation.REVERSE
    anim.repeatCount = Animation.INFINITE
    if (tracking) {
        /*view.startAnimation(anim)*/
        startAnimation(anim)
    } else {
        /*view.clearAnimation()*/
        clearAnimation()
    }
}

/**Extension method for Material Alert Dialog Using Higher order function.*/
/*inline fun Context.showAlertFunction(title: String, message: String, positiveBtnTxt: String = "Yes", negativeBtnTxt: String = "No", crossinline positiveBtnClick: () -> Unit) {
    MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
        .setCancelable(false)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtnTxt) { _, _ ->
            positiveBtnClick()
        }
        .setNegativeButton(negativeBtnTxt, null)
        .show()
}*/

fun TextView.addReadMore(maxLength: Int) {
    if (text.length <= maxLength) return
    var text: String = this.text.toString()
    if (text.contains("...Less")) text = text.replace("...Less", "")
    val ss = SpannableString(text.substring(0, maxLength) + "...More")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadLess(text, maxLength)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
            ds.color = resources.getColor(R.color.primary)
        }
    }
    ss.setSpan(clickableSpan, ss.length - 7, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = ss
    this.movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.addReadLess(text: String, maxLength: Int) {
    val ss = SpannableString("$text...Less")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadMore(maxLength)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
            ds.color = resources.getColor(R.color.primary)
        }
    }
    ss.setSpan(clickableSpan, ss.length - 7, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = ss
    this.movementMethod = LinkMovementMethod.getInstance()
}





