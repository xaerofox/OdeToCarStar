@file:Suppress("DSL_SCOPE_VIOLATION")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)

    kotlin("kapt")
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.jtor.odetocarstar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jtor.odetocarstar"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", "\"https://car-api2.p.rapidapi.com\"")
        buildConfigField("String", "RAPID_HOST", "\"car-api2.p.rapidapi.com\"")
        buildConfigField("String", "RAPID_API_KEY", "\"${gradleLocalProperties(rootDir).getProperty("API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Hilt
    kapt(libs.hilt.android.complier)
    implementation(libs.hilt.android)
    //implementation(libs.androidx.hilt.lifecycle.viewmodel)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3)

    //Moshi
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)

    //Coil
    implementation(libs.coil.compose)

    testImplementation(libs.androidx.core)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler.v237)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.core.ktx)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.runner)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}