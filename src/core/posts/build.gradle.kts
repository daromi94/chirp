plugins { alias(libs.plugins.kotlin.jvm) }

dependencies {
    implementation(project(":users"))

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
