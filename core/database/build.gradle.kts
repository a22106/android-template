plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.hilt)
    alias(libs.plugins.piusdev.template.room)
}

android {
    namespace = "com.piusdev.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
//    api(projects.core.model)
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(projects.core.testing)
}