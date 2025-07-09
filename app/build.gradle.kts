plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.saludtotalapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.saludtotalapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit(Para realizar request a APIS)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    
    // Gson (Permite serializar data class y JSON)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Picasso(Visor de imagenes a travez de urls)
    implementation("com.squareup.picasso:picasso:2.71828")

    // Material, investigar...
    implementation("com.google.android.material:material:1.11.0")

    
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")


    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


}