plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.piusdev.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.network)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(projects.core.testing)
}