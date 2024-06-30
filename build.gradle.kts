// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt")

            ktlint()
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }

        task("createSpotlessPreCommitHook") {
            val gitHooksDirectory = File("${project.rootDir}/.git/hooks/")
            if (!gitHooksDirectory.exists()) gitHooksDirectory.mkdirs()
            val preCommitFile = File("${project.rootDir}/.git/hooks", "pre-commit")
            if (!preCommitFile.exists()) preCommitFile.createNewFile()
            preCommitFile.writeText(
                """
                    #!/bin/bash
                    echo "Running spotless check"
                    ./gradlew spotlessApply
                    """,
            )
            project.exec {
                commandLine("chmod", "+x", "${project.rootDir}/.git/hooks/pre-commit")
            }
        }
    }
}
