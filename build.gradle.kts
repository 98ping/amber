import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    `maven-publish`
}

group = "ltd.matrixstudios"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4")
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4")
    testImplementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))
}

publishing {
    repositories {
        maven {
            name = "matrixMaven"
            url = uri("https://maven.matrixstudios.ltd/public")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "ltd.matrixstudios"
            artifactId = "amber"
            version = "1.1.0"
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}