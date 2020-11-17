// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
    }

    dependencies {
        classpath(Config.Plugins.gradle)
        classpath(Config.Plugins.kotlin)
        classpath(Config.Plugins.spotless)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
    }
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}
