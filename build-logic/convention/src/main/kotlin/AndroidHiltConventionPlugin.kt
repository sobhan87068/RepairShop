import com.example.repairshop.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(
                    libs
                        .findPlugin("ksp")
                        .get()
                        .get()
                        .pluginId,
                )
                apply(
                    libs
                        .findPlugin("hilt-android")
                        .get()
                        .get()
                        .pluginId,
                )
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt").get())

                add("ksp", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
