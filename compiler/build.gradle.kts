plugins {
    kotlin
    `kotlin-kapt`
    maven
}

uploadArchives(version = "1.1.0", artifactId = "compiler")

dependencies {
    implementation(Deps.`kotlin-stdlib`)
    kapt("com.google.auto.service:auto-service:1.0-rc6")
    implementation("com.google.auto.service:auto-service:1.0-rc6")
//    implementation(project(":annotation"))
    implementation("com.sorrowblue.android.kotpref:annotation:1.1.0")
}
