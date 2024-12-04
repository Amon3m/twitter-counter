plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.semicolon.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "API_KEY", "\"${project.property("API_KEY")}\"")
        buildConfigField("String", "API_SECRET_KEY", "\"${project.property("API_SECRET_KEY")}\"")
        buildConfigField("String", "ACCESS_TOKEN", "\"${project.property("ACCESS_TOKEN")}\"")
        buildConfigField("String", "ACCESS_TOKEN_SECRET", "\"${project.property("ACCESS_TOKEN_SECRET")}\"")

    }
    buildFeatures {
        buildConfig = true
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
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Coroutine
    implementation(libs.kotlinx.coroutines.android)
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.kotlinx.coroutines.test)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    //Logging Interceptor
    implementation (libs.logging.interceptor)


}