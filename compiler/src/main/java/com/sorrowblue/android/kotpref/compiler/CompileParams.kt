package com.sorrowblue.android.kotpref.compiler

internal class CompileParams(
    val name: String,
    val type: String,
    var value: String,
    var key: String? = null,
    var resId: Int? = null
) {
    fun toString(compileClassInfo: CompileClassInfo) =
        key?.let {
            "object $name :	${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, key = \"$it\")"
        } ?: resId?.let {
            "object $name :	${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, resId = $it)"
        } ?: "object $name : ${compileClassInfo.classRename ?: compileClassInfo.name}<$type>(${compileClassInfo.packageName}.${compileClassInfo.name}.${name}, key = \"$name\")"
}

