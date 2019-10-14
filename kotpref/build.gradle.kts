plugins {
    `android-library`
    `kotlin-android`
    `kotlin-android-extensions`
    maven
}

android {
    compileSdkVersion(Build.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.compileSdkVersion)
        versionCode = Build.versionCode
        versionName = Build.versionName

        testInstrumentationRunner = Build.androidJUnitRunner
        consumerProguardFile(Build.`consumer-rules`)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

uploadArchives(version = "1.0.0", artifactId = "kotpref")

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib-jdk7`)
    implementation(Deps.`core-ktx`)
    implementation(Deps.`preference-ktx`)
//    api(project(":annotation"))
    api("com.sorrowblue.android.kotpref:annotation:1.0.0")
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.`ext-junit`)
    androidTestImplementation(Deps.`espresso-core`)
}
