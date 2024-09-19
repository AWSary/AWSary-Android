plugins {
    alias(libs.plugins.lzcalderaro.android.library)
    alias(libs.plugins.lzcalderaro.jvm.ktor)
}

android {
    namespace = "com.android.lzcalderaro.iconsgame.data"
}

dependencies {

    implementation(libs.bundles.koin)

    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.iconsGame.domain)

    implementation(projects.core.data)
}