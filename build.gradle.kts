plugins {
    kotlin("jvm") version "1.6.10"
}

dependencies {
    api("com.github.ben-manes.caffeine:caffeine:2.9.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("io.kotest:kotest-assertions-jvm:4.0.7")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.register<Test>("testGraal") {
    javaLauncher.set(
        javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.GRAAL_VM)
        }
    )
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