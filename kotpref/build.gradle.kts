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


//tasks {
//    getByName<Upload>("uploadArchives") {
//        repositories {
//            withConvention(MavenRepositoryHandlerConvention::class) {
//                mavenDeployer {
//                    repository = "file://${rootDir}/docs/repository/"
//                    pom.version = "1.0.0"
//                    pom.groupId = "com.sorrowblue.android.kotpref"    // グループ名
//                    pom.artifactId = "kotpref" // ライブラリ名
//                }
//            }
//        }
//    }
//}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib-jdk7`)
    implementation(Deps.`core-ktx`)
    implementation(Deps.`preference-ktx`)
    api(project(":annotation"))
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.`ext-junit`)
    androidTestImplementation(Deps.`espresso-core`)
}
