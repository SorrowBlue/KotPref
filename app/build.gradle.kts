plugins {
    id("com.android.application")
    `kotlin-android`
    `kotlin-kapt`
    `kotlin-android-extensions`
}
android {
    compileSdkVersion(Build.compileSdkVersion)
    buildToolsVersion = Build.buildToolsVersion
    defaultConfig {
        applicationId = Build.applicationId
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.compileSdkVersion)
        versionCode = Build.versionCode
        versionName = Build.versionName
        testInstrumentationRunner = Build.androidJUnitRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Build.`proguard-android-optimize`),
                Build.`proguard-rules`
            )
        }
    }
    dataBinding { isEnabled = true }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib-jdk7`)
    implementation(Deps.appcompat)
    implementation(Deps.`core-ktx`)
    Deps.lifecycles.forEach { implementation(it) }
    implementation("androidx.activity:activity-ktx:1.1.0-beta01")
    implementation("com.google.android.material:material:1.1.0-beta01")
//    implementation(project(":kotpref"))
//    kapt(project(":compiler"))
    implementation("com.sorrowblue.android.kotpref:kotpref:1.1.0")
    kapt("com.sorrowblue.android.kotpref:compiler:1.1.0")
    implementation(Deps.constraintlayout)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.`ext-junit`)
    androidTestImplementation(Deps.`espresso-core`)
}
