package com.sorrowblue.android.kotpref.annotation

@Suppress("unused")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class KotPref(val keyPrefix: String = "", val classRename: String = "")
