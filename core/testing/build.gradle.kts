plugins {
    alias(libs.plugins.piusdev.template.library.compose)
    alias(libs.plugins.piusdev.template.hilt)

}

android {
    namespace = "com.piusdev.core.testing"
}

dependencies {
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test.junit4)
    api(projects.core.data)
//    api(projects.core.model)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}