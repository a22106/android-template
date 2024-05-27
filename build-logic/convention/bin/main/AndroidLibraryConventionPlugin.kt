import com.android.build.api.dsl.LibraryExtension
import com.piusdev.convention.ExtensionType
import com.piusdev.convention.configureBuildTypes
import com.piusdev.convention.configureKotlinAndroid
import com.piusdev.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                buildFeatures {
                    dataBinding = true
                    viewBinding = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.11"
                }

                configureKotlinAndroid(commonExtension = this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY
                )
            }

            dependencies {
                "testImplementation"(kotlin("test"))
                "implementation"(libs.findLibrary("androidx.tracing.ktx").get())
            }
        }
    }
}