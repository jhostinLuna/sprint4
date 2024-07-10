plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.daguerHilt)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")

}

android {
    namespace = "com.jhostinluna.sprint4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jhostinluna.sprint4"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation Component
    // Kotlin
    implementation(libs.bundles.navigation)
    //NavHostFragment
    api(libs.androidx.navigation.fragment.ktx)
    // Hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    //Room

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    //Coroutine Room
    implementation(libs.androidx.room.room)

    // To use Kotlin annotation processing tool (kapt)
    kapt(libs.androidx.room.compiler)

    //LifeCycleScope
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    // Maps SDK for Android
    implementation(libs.play.services.maps)
    //Firebase Boom
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")

}
kapt {
    correctErrorTypes = true
}