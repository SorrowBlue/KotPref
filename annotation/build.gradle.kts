import org.gradle.internal.impldep.org.apache.maven.artifact.ant.RemoteRepository

plugins {
    kotlin
    maven
}

tasks {
    getByName<Upload>("uploadArchives") {
        repositories {
            withConvention(MavenRepositoryHandlerConvention::class) {
                mavenDeployer {
                    withGroovyBuilder {
                        "repository"("url" to uri("${rootDir}/docs/repository/"))
                    }
                    pom.groupId = "com.sorrowblue.android.kotpref"
                    pom.version = "1.0.0"
                    pom.artifactId = "annotation"
                }
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib`)
}
