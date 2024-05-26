plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.hilt)
}

android {
    namespace = "com.piusdev.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}