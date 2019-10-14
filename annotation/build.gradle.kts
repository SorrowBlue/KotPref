plugins {
    kotlin
    maven
}

uploadArchives(version = "1.0.0", artifactId = "annotation")

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib`)
}
