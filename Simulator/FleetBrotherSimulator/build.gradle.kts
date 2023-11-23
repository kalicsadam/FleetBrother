import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "hu.bme.aut.fleetbrothersimulator"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://repo.eclipse.org/content/repositories/paho-snapshots/")
}
//dependencies{
//    compile 'org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.0.2'
//}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.0.2")
                implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.0.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "FleetBrotherSimulator"
            packageVersion = "1.0.0"
        }
    }
}
