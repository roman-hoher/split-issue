plugins {
    java
    id("io.quarkus")
    id("org.owasp.dependencycheck") version "12.1.0"
}

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://maven.repository.redhat.com/ga") }
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-camel-bom:${quarkusPlatformVersion}"))
    implementation("org.apache.camel.quarkus:camel-quarkus-core")
    implementation("org.apache.camel.quarkus:camel-quarkus-bean")
    implementation("org.apache.camel.quarkus:camel-quarkus-ognl")
    implementation("org.apache.camel.quarkus:camel-quarkus-direct")
    implementation("org.apache.camel.quarkus:camel-quarkus-log")
    implementation("org.apache.camel.quarkus:camel-quarkus-timer")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("org.apache.camel.quarkus:camel-quarkus-junit5")
}

dependencyCheck {
    analyzers.assemblyEnabled=false
    nvd {
        apiKey = System.getenv("NVD_API_KEY")
    }
}

group = "dev.issue.split"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

sourceSets {
    getByName("main") {
        java.srcDirs("src/main/java")
    }
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
