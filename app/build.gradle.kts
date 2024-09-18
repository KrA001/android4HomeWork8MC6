plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Hilt
    alias(libs.plugins.hilt.android)

    // Safe args
    id("androidx.navigation.safeargs.kotlin")

    // Kapt
    kotlin("kapt")
}

android {
    namespace = "com.example.android4homework8mc6"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android4homework8mc6"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //binding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)

    // AppCompat
    implementation(libs.androidx.appcompat)

    // Material Components
    implementation(libs.material)

    // Activity
    implementation(libs.androidx.activity)

    // UI Components
    implementation(libs.androidx.constraintlayout)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // OkHttp
    implementation(platform(libs.okHttp.bom))
    implementation(libs.okHttp)
    implementation(libs.okHttp.loggingInterceptor)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewModel)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.protolite.well.known.types)
    kapt(libs.hilt.compiler)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //SwipeRefreshLayout
    implementation(libs.swipeRefreshLayout)

    //navGraph
    implementation(libs.navGraph)
    implementation(libs.navGraph.ktx)

    // Paging
    implementation(libs.paging)

    // Glide
    implementation(libs.glide)

    // Binding property delegate
    implementation(libs.binding.property.delegate)

    // Fragment
    implementation(libs.fragment)
}