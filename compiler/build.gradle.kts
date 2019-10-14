plugins {
    kotlin
    `kotlin-kapt`
    maven
}

//tasks {
//    getByName<Upload>("uploadArchives") {
//        repositories {
//            withConvention(MavenRepositoryHandlerConvention::class) {
//                mavenDeployer {
//                    repository = "file://${rootDir}/docs/repository/"
//                    pom.version = "1.0.0"
//                    pom.groupId = "com.sorrowblue.android.kotpref"    // グループ名
//                    pom.artifactId = "compiler" // ライブラリ名
//                }
//            }
//        }
//    }
//}

dependencies {
    implementation(Deps.`kotlin-stdlib`)
    kapt("com.google.auto.service:auto-service:1.0-rc6")
    implementation("com.google.auto.service:auto-service:1.0-rc6")
    implementation(project(":annotation"))
}
