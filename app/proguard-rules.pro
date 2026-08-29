# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add this global rule
#-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models.
# Modify this rule to fit the structure of your app.
#-keepclassmembers class com.yourcompany.models.** {
#  *;
#}

-dontwarn com.android.lzcalderaro.core.data.di.CoreDataModuleKt
-dontwarn com.android.lzcalderaro.core.presentation.designsystem.ThemeKt
-dontwarn com.android.lzcalderaro.dictionary.data.di.DictionaryKoinModuleKt
-dontwarn com.android.lzcalderaro.dictionary.presentation.detail.DetailScreenKt
-dontwarn com.android.lzcalderaro.dictionary.presentation.detail.DetailViewModel
-dontwarn com.android.lzcalderaro.dictionary.presentation.di.DictionaryViewModelModuleKt
-dontwarn com.android.lzcalderaro.dictionary.presentation.list.ListScreenKt
-dontwarn com.android.lzcalderaro.dictionary.presentation.list.ListViewModel

-keep class com.android.lzcalderaro.core.data.di.** { *; }
-keep class com.android.lzcalderaro.dictionary.data.di.** { *; }
-keep class com.android.lzcalderaro.dictionary.presentation.di.** { *; }
-keep class com.android.lzcalderaro.dictionary.presentation.** { *; }
