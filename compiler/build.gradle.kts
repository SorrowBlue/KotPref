plugins {
    kotlin
    `kotlin-kapt`
    maven
}


dependencies {
    implementation(Deps.`kotlin-stdlib`)
    kapt("com.google.auto.service:auto-service:1.0-rc6")
    implementation("com.google.auto.service:auto-service:1.0-rc6")
    implementation(project(":annotation"))
}
