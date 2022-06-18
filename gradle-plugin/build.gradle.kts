@file:Suppress("UnstableApiUsage")

import com.gradle.publish.MavenCoordinates
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.utils.addToStdlib.cast

plugins {
  id("java-gradle-plugin")
  kotlin("jvm")
  id("com.github.gmazzo.buildconfig")
  id("com.gradle.plugin-publish")
  id("convention.publication")
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation(kotlin("gradle-plugin-api"))
}

buildConfig {
  val project = project(":kotlin-plugin")
  packageName(project.group.toString().replace("-", ""))
  buildConfigField(
    "String",
    "KOTLIN_PLUGIN_ID",
    "\"${rootProject.extra["kotlin_plugin_id"].toString().replace("-", "")}\""
  )
  buildConfigField("String", "KOTLIN_PLUGIN_GROUP", "\"${project.group}\"")
  buildConfigField("String", "KOTLIN_PLUGIN_NAME", "\"${project.name}\"")
  buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${project.version}\"")
  val preludeProject = project(":prelude")
  buildConfigField("String", "PRELUDE_LIBRARY_GROUP", "\"${preludeProject.group}\"")
  buildConfigField("String", "PRELUDE_LIBRARY_NAME", "\"${preludeProject.name}\"")
  buildConfigField("String", "PRELUDE_LIBRARY_VERSION", "\"${preludeProject.version}\"")

}

java {
  withSourcesJar()
  withJavadocJar()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}
val pluginDescription =
  "A Kotlin compiler plugin that automatically makes every function/property inside an object static"
val pluginName = "kotlin-all-static"
val pluginDisplayName = "Kotlin All Static compiler plugin"
gradlePlugin {
  plugins {
    create(pluginName) {
      id = "io.github.kyay10.kotlin-all-static"
      displayName = pluginDisplayName
      description = pluginDescription
      implementationClass = "io.github.kyay10.kotlinallstatic.AllStaticGradlePlugin"
    }
  }
}
pluginBundle {
  website = "https://github.com/kyay10/kotlin-all-static"
  vcsUrl = website
  description = pluginDescription

  version = rootProject.version.toString()
  (plugins) {
    pluginName {
      displayName = pluginDisplayName
      tags = listOf(
        "kotlin"
      )
      version = rootProject.version.toString()
    }
  }
  val mavenCoordinatesConfiguration = { coords: MavenCoordinates ->
    coords.groupId = group.cast()
    coords.artifactId = name
    coords.version = version.cast()
  }

  mavenCoordinates(mavenCoordinatesConfiguration)
}
