package com.cohen.binaware.helpers

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isInvisible(): Boolean {
    return this.visibility == View.INVISIBLE
}

fun Fragment.post100(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 100)
}

fun Fragment.post(int: Long, function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, int)
}

fun View.post(int: Long, function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, int)
}

fun Fragment.post300(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 300)
}

fun Activity.post100(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 100)
}

fun Activity.post(int: Long, function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, int)
}

fun Activity.post300(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 300)
}

fun Fragment.post600(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 600)
}

fun Fragment.post900(function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed(function, 900)
}

fun Fragment.post(function: () -> Unit) {
    val handler = Handler()
    handler.post(function)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_IMPLICIT)
}
fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Activity.showKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { showKeyboard(it) }
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}
