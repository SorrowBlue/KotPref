package com.sorrowblue.android.kotpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

fun Context.sharedPreferences(name: String? = null): SharedPreferences =
    name?.let { getSharedPreferences(it, Context.MODE_PRIVATE) }
        ?: PreferenceManager.getDefaultSharedPreferences(this)

@Suppress("unused")
fun Fragment.sharedPreferences(name: String? = null): SharedPreferences =
    requireContext().sharedPreferences(name)


@Suppress("UNCHECKED_CAST", "unused")
internal fun <T : Any> SharedPreferences.putValue(
    key: String,
    value: T,
    isCommit: Boolean = false
): Unit = edit(commit = isCommit) {
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Float -> putFloat(key, value)
        is Long -> putLong(key, value)
        else -> {
            if (value is Set<*> && value.all { it is String }) {
                putStringSet(key, value as Set<String>)
            } else {
                throw RuntimeException("${value::class.java.simpleName} type is not supported.")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> SharedPreferences.getValue(key: String, default: T): T = when (default) {
    is String -> getString(key, default) ?: default
    is Int -> getInt(key, default)
    is Long -> getLong(key, default)
    is Float -> getFloat(key, default)
    is Boolean -> getBoolean(key, default)
    else -> {
        if (default is Set<*> && default.all { it is String }) {
            getStringSet(key, default as Set<String>)
        } else {
            throw RuntimeException("${default::class.java.simpleName} type is not supported.")
        }
    }
} as T
