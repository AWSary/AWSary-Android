plugins {
    alias(libs.plugins.lzcalderaro.android.feature.ui)
}


android {
    namespace = "com.android.lzcalderaro.iconsgame.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.iconsGame.domain)
    implementation(projects.core.presentation.designsystem)
}