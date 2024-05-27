import com.android.build.api.dsl.ApplicationExtension
import com.piusdev.convention.ExtensionType
import com.piusdev.convention.configureBuildTypes
import com.piusdev.convention.configureKotlinAndroid
import com.piusdev.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            // app 모듈에 적용할 플러그인
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension>{
                buildFeatures { // TODO: dataBinding, viewBinding은 View를 사용하는 feature에서만 사용하도록 분리. AndroidApplicationViewConventionPlugin.kt을 만들어 별도로 관리
                    dataBinding = true
                    viewBinding = true
                }

                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdk").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(commonExtension = this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION)
            }
        }
    }
}