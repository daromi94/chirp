plugins { alias(libs.plugins.kotlin.jvm) }

dependencies { testImplementation(libs.kotlin.test) }

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
