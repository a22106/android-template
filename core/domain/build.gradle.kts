plugins {
    alias(libs.plugins.piusdev.template.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.piusdev.core.domain"
}

dependencies {
    api(projects.core.data)
//    api(projects.core.model)

    implementation(libs.javax.inject)

    testImplementation(projects.core.testing)
}