plugins {
    alias(libs.plugins.lzcalderaro.android.library.compose)
}

android {
    namespace = "com.android.lzcalderaro.core.presentation.designsystem"
}

dependencies {

    implementation(libs.coil.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.material.icons.extended)
    api(libs.androidx.compose.material3)
}
