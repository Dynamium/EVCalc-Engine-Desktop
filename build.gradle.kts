import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.compose") version "0.2.0-build132"
}

group = "org.dynamium.evcalc"
version = "1.2"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    maven { url = uri("https://maven.pkg.jetbrains.space/dynamium/p/evc/maven") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.dynamium.evcalc:evcalc-engine:1.0-dev6")
    implementation("io.ktor:ktor-client-cio:1.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "org.dynamium.evcalc.desktop.MainKt"
        nativeDistributions {

            packageName = "EVCalc Engine Desktop"
            version = "1.2"
            description = "EVCalc Desktop app for calculating mileage on one charge and more for PEVs."

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            macOS {
                iconFile.set(project.file("appIcons/EVCalc Engine Desktop.icns"))
            }

            linux {
                iconFile.set(project.file("appIcons/EVCalc Engine Desktop.png"))
            }
        }
    }
}
// ©