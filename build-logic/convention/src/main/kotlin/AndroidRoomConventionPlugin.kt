import androidx.room.gradle.RoomExtension
import com.example.repairshop.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(
                    libs
                        .findPlugin("room")
                        .get()
                        .get()
                        .pluginId,
                )
                apply(
                    libs
                        .findPlugin("ksp")
                        .get()
                        .get()
                        .pluginId,
                )
            }

            extensions.configure<RoomExtension> {
                // The schemas directory contains a schema file for each version of the Room database.
                // This is required to enable Room auto migrations.
                // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                add("implementation", libs.findLibrary("room").get())
                add("implementation", libs.findLibrary("room.ktx").get())
                add("implementation", libs.findLibrary("room.paging").get())

                add("ksp", libs.findLibrary("room.compiler").get())

                add("testImplementation", libs.findLibrary("room.testing").get())
            }
        }
    }
}
