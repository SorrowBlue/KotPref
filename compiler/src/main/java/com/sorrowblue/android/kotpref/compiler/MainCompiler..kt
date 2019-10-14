package com.sorrowblue.android.kotpref.compiler

import com.google.auto.service.AutoService
import com.sorrowblue.android.kotpref.annotation.KotPref
import com.sorrowblue.android.kotpref.annotation.KotPrefKey
import java.io.File
import java.util.*
import javax.annotation.processing.*

import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

private const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
    "com.sorrowblue.android.kotpref.annotation.KotPref",
    "com.sorrowblue.android.kotpref.annotation.KotPrefKey"
)
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
class MainCompiler : AbstractProcessor() {
    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        val list =
            kotlin.runCatching {
                roundEnv.getElementsAnnotatedWith(KotPref::class.java).map {
                    CompileClassInfo(
                        name = it.className,
                        packageName = it.packageName,
                        keyPrefix = it.getAnnotation<KotPref>().keyPrefix.ifEmpty { null },
                        classRename = it.getAnnotation<KotPref>().classRename.ifEmpty { null },
                        params = it.compileParamsList.toMutableList()
                    )
                }
            }.onFailure {
                this.processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, it.toString())
            }.getOrNull() ?: return false
        roundEnv.getElementsAnnotatedWith(KotPrefKey::class.java).map { element ->
            val thisClass = element.enclosingElement.simpleName.toString()
            list.find { it.name == thisClass }?.let {
                val fieldName = element.simpleName.toString().replace("\$annotations", "")
                it.params.forEach {
                    if (it.name == fieldName) {
                        it.key = element.getAnnotation(KotPrefKey::class.java).key.let {
                            if (it != "") it else null
                        }
                        it.resId = element.getAnnotation(KotPrefKey::class.java).resId.let {
                            if (it != 0) it else null
                        }
                    }
                }
            }
        }
        list.forEach { ko ->
            val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
                ?: run {
                    processingEnv.messager.printMessage(
                        Diagnostic.Kind.ERROR,
                        "Can't find the target directory for generated Kotlin files."
                    )
                    return@forEach
                }
            val file =
                File(kaptKotlinGeneratedDir, ko.packageName.replace(".", "/") + "/preference/").let {
                    if (it.exists().not()) {
                        it.mkdirs()
                    }
                    File(it, "${ko.classRename ?: ko.name}.kt")
                }
            val text = """
			package ${ko.packageName}.preference

            import com.sorrowblue.android.kotpref.IKotPrefKey

			sealed class ${ko.classRename ?: ko.name}<T : Any>(default: T, key: String? = null, resId: Int? = null) : IKotPrefKey<T>(default, key, resId) {
				
				override val prefix = ${ko.keyPrefix?.let { "\"$it\"" }  ?: "null"}
				
				${ko.params.map { (it.toString(ko)) }.joinToString("\n\t\t\t\t")}
			}
""".trimIndent()
            file.writeText(text)
        }
        return true
    }
}
