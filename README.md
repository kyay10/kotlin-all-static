# kotlin-all-static

![Maven Central](https://img.shields.io/maven-central/v/io.github.kyay10.kotlin-all-static/kotlin-plugin?color=gree) (Compiler plugin)
![Maven Central](https://img.shields.io/maven-central/v/io.github.kyay10.kotlin-all-static/gradle-plugin?color=gree) (Gradle Plugin)

[![](https://jitpack.io/v/kyay10/kotlin-all-static.svg)](https://jitpack.io/#kyay10/kotlin-all-static)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v?color=gree&label=gradlePluginPortal&metadataUrl=https%3A%2F%2Fplugins.gradle.org%2Fm2%2Fio%2Fgithub%2Fkyay10%2Fkotlin-all-static%2Fio.github.kyay10.kotlin-all-static.gradle.plugin%2Fmaven-metadata.xml)](https://plugins.gradle.org/plugin/io.github.kyay10.kotlin-all-static)

A Kotlin compiler plugin that automatically makes every function and property under an object static. In other words, it applies the `@JvmStatic` annotation to every property or function under an `object` or `companion object`

Use with care!
Note: This plugin was created
using [Brian Norman's Kotlin IR Plugin Template](https://github.com/bnorm/kotlin-ir-plugin-template) and from guidance
from his wonderful article
series [Writing Your Second Kotlin Compiler Plugin](https://blog.bnorm.dev/writing-your-second-compiler-plugin-part-1) (
seriously like the articles were immensely helpful when I just knew absolutely nothing about IR)
and it was created originally as part of the discussion
on [KotlinLang Slack: Spock Testing: Is it possible to make kotlin use @JvmStatic by default for generated methods](https://www.linen.dev/s/kotlinlang/t/495084/i-m-using-spock-for-testing-it-won-t-be-changed-is-it-possib)
to support the OP's use-case.
