package com.sorrowblue.android.kotpref.annotation

@Suppress("unused")
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class KotPrefKey(val key: String = "", val resKey: String = "")
