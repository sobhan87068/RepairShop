plugins {
    alias(libs.plugins.repairshop.android.application.compose)
    alias(libs.plugins.repairshop.android.hilt)
    alias(libs.plugins.repairshop.android.room)
}

android {
    namespace = "com.example.repairshop"

    defaultConfig {
        applicationId = "com.example.repairshop"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
