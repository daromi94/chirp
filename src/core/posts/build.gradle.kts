plugins { alias(libs.plugins.kotlin.jvm) }

group = "com.daromi.chirp.core.posts"

version = "0.1.0"

repositories { mavenCentral() }

dependencies {
    implementation(project(":users"))

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
