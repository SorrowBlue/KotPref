import org.gradle.api.plugins.MavenRepositoryHandlerConvention
import org.gradle.api.tasks.Upload
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate
import org.gradle.kotlin.dsl.withConvention
import org.gradle.kotlin.dsl.withGroovyBuilder

fun ProjectDelegate.uploadArchives(version: String, artifactId: String) {
    tasks.getByName<Upload>("uploadArchives") {
        repositories {
            withConvention(MavenRepositoryHandlerConvention::class) {
                mavenDeployer {
                    withGroovyBuilder {
                        "repository"("url" to uri("${rootDir}/docs/repository/"))
                    }
                    pom.groupId = "com.sorrowblue.android.kotpref"
                    pom.version = version
                    pom.artifactId = artifactId
                }
            }
        }
    }
}
