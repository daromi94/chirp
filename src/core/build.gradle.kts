plugins { alias(libs.plugins.kotlin.jvm) apply false }

group = "com.daromi.chirp.core"

version = "0.1.0-SNAPSHOT"

subprojects {
    repositories { mavenCentral() }
}
