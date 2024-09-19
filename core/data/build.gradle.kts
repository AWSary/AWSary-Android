plugins {
    alias(libs.plugins.lzcalderaro.android.library)
    alias(libs.plugins.lzcalderaro.jvm.ktor)
}

android {
    namespace = "com.android.lzcalderaro.core.data"
}

dependencies {

    implementation(kotlin("reflect"))

    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
}