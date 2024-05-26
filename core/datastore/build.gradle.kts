plugins {
    alias(libs.plugins.piusdev.template.library)
    alias(libs.plugins.piusdev.template.hilt)
}

android {
    namespace = "com.piusdev.core.datastore"
    defaultConfig{
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    testOptions{
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}