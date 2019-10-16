package com.sorrowblue.android.kotpref

import android.content.Context
import kotlin.properties.ReadWriteProperty

abstract class IKotPrefKey<T : Any>(
    val default: T,
    val key: String?,
    val resKey: String?
) {
    abstract val prefix: String?
    fun getName(context: Context): String {
        val key = resKey?.let {
            context.resources.getIdentifier(
                it,
                "string",
                context.packageName
            )
        }?.let(context::getString)
            ?: key ?: this::class.java.simpleName
        return prefix?.let { "$it$key" } ?: key
    }
}

abstract class KotPrefKeyDelegate<R : Any, T : Any>(
    val kotPrefKey: IKotPrefKey<T>,
    val isCommit: Boolean,
    val name: String?
) : ReadWriteProperty<R, T>
