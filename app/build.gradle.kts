plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.jupiterreporting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jupiterreporting"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }

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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0"
            )
        }
    }
}

dependencies {
    // Основные библиотеки
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.activity:activity-compose:1.7.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Room (для работы с базами данных)
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    // WorkManager (для фоновых задач)
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // Google API Client и Sheets API
    implementation("com.google.api-client:google-api-client:1.34.1")
    implementation("com.google.http-client:google-http-client-jackson2:1.39.2")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20230227-2.0.0")

    // Google Auth Library
    implementation("com.google.auth:google-auth-library-oauth2-http:1.17.0")
    // NetHttpTransport
    implementation("com.google.http-client:google-http-client:1.39.2")

    // Gson (для работы с JSON)
    implementation("com.google.code.gson:gson:2.10.1")

    // Desugaring (для использования современных API на старых версиях Android)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    // Модульные тесты
    testImplementation("junit:junit:4.13.2")

    // Инструментальные тесты
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
