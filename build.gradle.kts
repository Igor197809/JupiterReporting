// build.gradle.kts (Project-level)
plugins {
    id("com.android.application") version "8.6.1" apply false
    kotlin("android") version "1.9.10" apply false
    kotlin("kapt") version "1.9.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}