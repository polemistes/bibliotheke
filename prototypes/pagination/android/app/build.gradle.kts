// SPDX-License-Identifier: GPL-3.0-or-later
plugins {
    id("com.android.application")
}

android {
    namespace = "app.bibliotheke.wikisource.proto"
    compileSdk = 36

    defaultConfig {
        // Distinct from the real app id so the prototype never collides with it.
        applicationId = "app.bibliotheke.wikisource.proto"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1-wp0"
    }

    // The browser harness and the WebView load the same reader files.
    sourceSets.getByName("main").assets.srcDir("../../web")

    buildTypes {
        release {
            isMinifyEnabled = false
            // Prototype only: release build (realistic performance) signed with the debug key.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.webkit:webkit:1.14.0")
}
