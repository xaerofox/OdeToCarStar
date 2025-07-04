// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.android.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
}