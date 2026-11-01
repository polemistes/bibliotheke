// SPDX-License-Identifier: GPL-3.0-or-later
// Content (PLAN.md 7): live part resolution, the Parsoid fetcher, the Jsoup normaliser,
// synthetic parts and locator text, as a plain Kotlin module tested against the fixture
// corpus on the host (PLAN.md 7.3, 10).
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
    api(project(":core-catalog"))
    api(libs.jsoup)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockwebserver)
}

tasks.test {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    systemProperty("fixturesDir", rootProject.file("app/src/test/fixtures").absolutePath)
    systemProperty("namespacesJson", rootProject.file("docs/wp0/namespaces.json").absolutePath)
    // -PupdateGolden=true rewrites expected.html / expected.json from the current output.
    systemProperty("updateGolden", (project.findProperty("updateGolden") ?: "false").toString())
}
