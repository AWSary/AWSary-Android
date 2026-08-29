plugins {
    alias(libs.plugins.lzcalderaro.android.application.compose)
    alias(libs.plugins.lzcalderaro.jvm.ktor)
}

val certificatePath: String by project
val localStorePassword: String by project
val localKeyAlias: String by project
val localKeyPassword: String by project

android {
    namespace = "com.lzcalderaro.awsary"

    signingConfigs {
        create("release") {
            storeFile = file(certificatePath)
            storePassword = localStorePassword
            keyAlias = localKeyAlias
            keyPassword = localKeyPassword
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.material)

    // Test Implementation
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Crypto
    implementation(libs.androidx.security.crypto.ktx)

    implementation(libs.bundles.koin)

    implementation(libs.compose.markdown)
    implementation(libs.coil.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    // Timber
    implementation(libs.timber)

    //api(libs.core)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(projects.dictionary.presentation)
    implementation(projects.dictionary.domain)
    implementation(projects.dictionary.data)

    implementation(projects.iconsGame.domain)
    implementation(projects.iconsGame.presentation)
    implementation(projects.iconsGame.data)
}
