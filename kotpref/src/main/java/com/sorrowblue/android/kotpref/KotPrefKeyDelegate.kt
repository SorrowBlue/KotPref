package com.sorrowblue.android.kotpref

import kotlin.properties.ReadWriteProperty

abstract class KotPrefKeyDelegate<R : Any, T : Any>(
    val kotPrefKey: IKotPrefKey<T>,
    val isCommit: Boolean,
    val name: String?
) : ReadWriteProperty<R, T>
