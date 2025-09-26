plugins {
    java
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.nerobeev"
version = "0.0.1-SNAPSHOT"
description = "Java-based economic planning system"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

// Переменные версий для согласованности
ext {
    set("mapstructVersion", "1.5.5.Final")
    set("lombokVersion", "1.18.30")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.openapitools:jackson-databind-nullable:0.2.7")

    // MapStruct - ИСПРАВЛЕНО: добавлена версия
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")

    // Математические вычисления
    implementation("org.ejml:ejml-all:0.43.1")

    // Development tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    // Lombok
    compileOnly("org.projectlombok:lombok:${property("lombokVersion")}")
    annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")

    // MapStruct processors -> правильный порядок
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// ДОБАВЛЕНО: Настройка компиляции для MapStruct + Lombok
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf(
        "-Amapstruct.defaultComponentModel=spring",
        "-Amapstruct.unmappedTargetPolicy=IGNORE"
    ))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
