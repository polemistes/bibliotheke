// SPDX-License-Identifier: GPL-3.0-or-later
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

// Catalog host (D6): the assets of the rolling release `catalog-latest`. Override with
// -PcatalogBaseUrl=…; debug builds default to a local server on the emulator's host
// (`python3 -m http.server 8765` in pipeline/out) until the CI release exists.
val releaseCatalogUrl = "https://github.com/polemistes/bibliotheke/releases/download/catalog-latest/"
val catalogBaseUrl = providers.gradleProperty("catalogBaseUrl")

android {
    namespace = "app.bibliotheke.wikisource"
    compileSdk = 37

    defaultConfig {
        applicationId = "app.bibliotheke.wikisource"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0-dev"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "CATALOG_BASE_URL", "\"${catalogBaseUrl.getOrElse("http://10.0.2.2:8765/")}\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "CATALOG_BASE_URL", "\"${catalogBaseUrl.getOrElse(releaseCatalogUrl)}\"")
            // Until a release key exists (WP7), release builds are signed with the debug key.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    androidResources {
        generateLocaleConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions.unitTests.isReturnDefaultValues = true
}

// Room schemas, for migration tests once there is a second version (PLAN.md 10).
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(":core-catalog"))
    implementation(project(":core-content"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.work.runtime)
    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.viewmodel.compose)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.webkit)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit)
}
