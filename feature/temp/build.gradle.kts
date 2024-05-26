plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.hilt)
    alias(libs.plugins.piusdev.template.jvm.ktor)
    alias(libs.plugins.piusdev.template.feature.ui)
}


android {
    namespace = "com.piusdev.feature.temp"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.network)

    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
}