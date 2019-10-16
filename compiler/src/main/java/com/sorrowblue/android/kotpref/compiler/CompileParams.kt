package com.sorrowblue.android.kotpref.compiler

internal class CompileParams(
    val name: String,
    val type: String,
    var value: String,
    var key: String? = null,
    var resKey: String? = null
) {
    fun toString(compileClassInfo: CompileClassInfo) =
        key?.let {
            "object $name :	${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, key = \"$it\")"
        } ?: resKey?.let {
            "object $name :	${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, resKey = \"$it\")"
        } ?: "object $name : ${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, key = \"$name\")"
}

