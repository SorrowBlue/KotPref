package com.sorrowblue.android.kotpref.compiler

internal class CompileClassInfo(
    val name: String,
    val packageName: String,
    val keyPrefix: String?,
    val classRename: String?,
    val params: MutableList<CompileParams> = mutableListOf()
)
