plugins {
    `kotlin-dsl`
}

group = "com.android.lzcalderaro.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}


gradlePlugin {
    plugins {

        register("androidApplication") {
            id = "lzcalderaro.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "lzcalderaro.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "lzcalderaro.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "lzcalderaro.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = "lzcalderaro.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("jvmKtor") {
            id = "lzcalderaro.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }

        register("androidFeatureUi") {
            id = "lzcalderaro.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }

    }
}