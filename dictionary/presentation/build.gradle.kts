plugins {
    alias(libs.plugins.lzcalderaro.android.feature.ui)
}

android {
    namespace = "com.android.lzcalderaro.dictionary.presentation"
}

dependencies {
    implementation(libs.compose.markdown)
    implementation(projects.core.domain)
    implementation(projects.dictionary.domain)
    implementation(projects.core.presentation.designsystem)
}