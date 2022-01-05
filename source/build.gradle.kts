plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation("com.github.ben-manes.caffeine:caffeine:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("io.kotest:kotest-assertions-jvm:4.0.7")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

application {
    mainClass.set("CaffeineEvictKt")
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "CaffeineEvictKt"
    }
}

tasks.register<Test>("test16") {
    javaLauncher.set(
        javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
    )
}

tasks.register<Test>("test17") {
    javaLauncher.set(
        javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    google()
    mavenCentral()
}