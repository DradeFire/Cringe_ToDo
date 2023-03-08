package com.cringeteam.todoproject.common.logger

import android.util.Log

object Logger {
    private const val TAG = "LOG_TAG"

    fun log (message: String, e: Throwable? = null) {
        Log.d(TAG, message, e)
    }
}

