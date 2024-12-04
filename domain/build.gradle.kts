plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation (libs.kotlinx.coroutines.test)
//    //Hilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//    kapt(libs.androidx.hilt.compiler)

}