package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val v = currentFocus ?: View(this)

    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(v.windowToken, 0)
    return
}

var isKeyboardOpen: Boolean = false

fun Activity.registerKeyboardStateListener() {
    val activityRootView = window.decorView.rootView
    activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        //r will be populated with the coordinates of your view that area still visible.
        activityRootView.getWindowVisibleDisplayFrame(r)

        val heightDiff = activityRootView.rootView.height - r.height()
        // if more than 25% of the screen, its probably a keyboard...
        isKeyboardOpen = heightDiff > 0.25 * activityRootView.rootView.height
    }
}

fun Activity.isKeyboardOpen() = isKeyboardOpen

fun Activity.isKeyboardClosed() = !isKeyboardOpen