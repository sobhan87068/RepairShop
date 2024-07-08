import com.android.build.api.dsl.ApplicationExtension
import com.example.repairshop.configureAndroidCompose
import com.example.repairshop.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(
                    libs
                        .findPlugin("repairshop-android-application")
                        .get()
                        .get()
                        .pluginId,
                )

                apply(
                    libs
                        .findPlugin("compose-compiler")
                        .get()
                        .get()
                        .pluginId,
                )
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}
