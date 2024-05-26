plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.library.compose)
}

android {
    namespace = "com.piusdev.core.ui"
    defaultConfig{
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    androidTestImplementation(projects.core.testing)
}