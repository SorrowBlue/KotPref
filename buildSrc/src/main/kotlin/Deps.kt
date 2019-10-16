@Suppress("ObjectPropertyName")
object Deps {
    const val `android-tools-build-gradle` =
        "com.android.tools.build:gradle:${Version.`android-tools-build-gradle`}"
    const val `kotlin-gradle-plugin` = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    const val `kotlin-stdlib` = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val `kotlin-stdlib-jdk7` = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

    const val `core-ktx` = "androidx.core:core-ktx:${Version.`core-ktx`}"
    const val `preference-ktx` = "androidx.preference:preference-ktx:${Version.`preference-ktx`}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"

    private const val `lifecycle-extensions` =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    private const val `lifecycle-common-java8` =
        "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"
    private const val `lifecycle-viewmodel-ktx` =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    private const val `lifecycle-livedata-ktx` =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"

    val lifecycles = listOf(
        `lifecycle-extensions`,
        `lifecycle-livedata-ktx`,
        `lifecycle-viewmodel-ktx`,
        `lifecycle-common-java8`
    )

    const val junit = "junit:junit:${Version.junit}"
    const val `ext-junit` = "androidx.test.ext:junit:${Version.`ext-junit`}"
    const val `espresso-core` = "androidx.test.espresso:espresso-core:${Version.`espresso-core`}"
}
