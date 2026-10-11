plugins {
    // plugin versioni definite nel catalogo; alias usati nei sottomoduli
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.compose.multiplatform) apply false
}