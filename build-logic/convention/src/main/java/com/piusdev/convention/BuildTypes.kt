package com.piusdev.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
){
    commonExtension.run {
        buildFeatures{
            buildConfig = true
        }
        val mapseaApiUrl = gradleLocalProperties(rootDir, providers = project.providers).getProperty("MAPSEA_API_URL")
        val navigationApiUrl = gradleLocalProperties(rootDir, providers = project.providers).getProperty("NAVIGATION_API_URL")
        when(extensionType){
            // Android 애플리케이션 모듈을 위한 설정. 서명 구성(signing configurations), ProGuard 설정, 빌드 타입(build types) 등
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(mapseaApiUrl, navigationApiUrl)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, mapseaApiUrl, navigationApiUrl)
                        }
                    }
                }
            }
            // Android 라이브러리 모듈을 위한 설정을 제공. 빌드 타입(build types) 및 기타 라이브러리 관련 설정
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(mapseaApiUrl, navigationApiUrl)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, mapseaApiUrl, navigationApiUrl)
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(
    baseUrl: String,
    navigationApiUrl: String){
    buildConfigField("String", "MAPSEA_API_URL", "\"$baseUrl\"")
    buildConfigField("String", "NAVIGATION_API_URL", "\"$navigationApiUrl\"")
    buildConfigField("String", "BASE_ENC_URL", "\"enc_http_dev\"")
    buildConfigField("String", "ENC_TCP", "\"enc_server_dev\"")
    buildConfigField("String", "WEBSOCKET", "\"ais_socket_dev\"")
    buildConfigField("Integer", "WS_UPDATE_INTERVAL", "3000")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    baseUrl: String,
    navigationApiUrl: String
){
    buildConfigField("String", "MAPSEA_API_URL", "\"$baseUrl\"")
    buildConfigField("String", "NAVIGATION_API_URL", "\"$navigationApiUrl\"")
    buildConfigField("String", "BASE_ENC_URL", "\"enc_http\"")
    buildConfigField("String", "ENC_TCP", "\"enc_server\"")
    buildConfigField("String", "WEBSOCKET", "\"ais_socket\"")
    buildConfigField("Integer", "WS_UPDATE_INTERVAL", "800")

    isMinifyEnabled = false // 코드 난독화
    isShrinkResources = false // 리소스 축소
    proguardFiles( // ProGuard 설정: 코드 최적화
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}