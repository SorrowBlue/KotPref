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
                    pom.project {
                        withGroovyBuilder {
                            "parent" {
                                "groupId"("com.sorrowblue.android.kotpref")
                                "artifactId"("annotation")
                                "version"("1.0.0")
                            }
                            "licenses" {
                                "license" {
                                    "name"("The Apache Software License, Version 2.0")
                                    "url"("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                    "distribution"("repo")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(Deps.`kotlin-stdlib`)
}
