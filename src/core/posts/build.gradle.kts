plugins { alias(libs.plugins.kotlin.jvm) }

group = "com.daromi.chirp"

version = "0.1.0"

repositories { mavenCentral() }

dependencies {
    // Internal
    implementation(project(":users"))

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
