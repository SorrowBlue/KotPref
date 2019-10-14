plugins {
    kotlin
    maven
}

uploadArchives(version = "1.0.1", artifactId = "annotation")

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib`)
}
