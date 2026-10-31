// SPDX-License-Identifier: GPL-3.0-or-later
// The catalog layer (PLAN.md 6.1) as a plain Kotlin module, so its queries run in
// host unit tests against real catalogs with the same bundled SQLite (FTS5) as the app.
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions.jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
}

dependencies {
    api(libs.androidx.sqlite)
    api(libs.androidx.sqlite.bundled)
    api(libs.kotlinx.coroutines.core)
    api(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockwebserver)
}

tasks.test {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    // Real catalogs from `make catalogs`, if present (gitignored); tests that need them skip otherwise.
    systemProperty("catalogDir", rootProject.file("pipeline/out").absolutePath)
}
