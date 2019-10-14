package com.sorrowblue.android.kotpref.compiler

import com.sun.corba.se.impl.io.TypeMismatchException
import javax.lang.model.element.Element
import javax.lang.model.element.PackageElement
import javax.lang.model.element.VariableElement

internal val Element.packageName get() = (enclosingElement as PackageElement).qualifiedName.toString()
internal val Element.className get() = simpleName.toString()
internal val Element.fieldName get() = simpleName.toString()

internal val Element.fields
    get() = enclosedElements.filter { it.kind.isField && it.simpleName.toString() != "INSTANCE" }


internal val Element.kotlinPrimitive
    get() = when (val type = (this as VariableElement).asType().toString()) {
        "int" -> "Int"
        "java.lang.String" -> "String"
        "boolean" -> "Boolean"
        "float" -> "Float"
        "long" -> "Long"
        "double" -> "Double"
        "java.util.Set<java.lang.String>" -> "Set<String>"
        else -> throw TypeMismatchException("KotlinSharedPreference is not support $type(${enclosingElement.simpleName}.$className).")
    }

internal val Element.constantAutoValue
    get() = when ((this as VariableElement).asType().toString()) {
        "float" -> "${constantValue}f"
        "java.lang.String" -> """ "$constantValue" """.trim(' ')
        "java.util.Set<java.lang.String>" -> "emptySet()"
        else -> constantValue?.toString() ?: "null"
    }

internal inline fun <reified T : Annotation> Element.getAnnotation() = getAnnotation(T::class.java)


internal val Element.compileParamsList
    get() = fields.map {
        CompileParams(name = it.fieldName, type = it.kotlinPrimitive, value = it.constantAutoValue)
    }

