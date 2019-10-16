package com.sorrowblue.android.kotpref

import android.content.Context

abstract class IKotPrefKey<T : Any>(
    val default: T,
    @Suppress("MemberVisibilityCanBePrivate") val key: String?,
    @Suppress("MemberVisibilityCanBePrivate") val resKey: String?
) {
    abstract val prefix: String?
    fun getName(context: Context): String {
        val key = resKey?.let {
            context.resources.getIdentifier(it, "string", context.packageName)
        }?.let(context::getString)
            ?: key ?: this::class.java.simpleName
        return prefix?.let { "$it$key" } ?: key
    }
}

