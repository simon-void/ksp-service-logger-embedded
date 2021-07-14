plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("org.springframework:spring-webmvc:5.3.8")
    implementation(project(":logging-processor"))
    ksp(project(":logging-processor"))
}

ksp {
    arg("option1", "value1")
    arg("option2", "value2")
}
