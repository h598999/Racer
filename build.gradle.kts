plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.Game"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.postgresql:postgresql:42.6.0")
  // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
  implementation("io.jsonwebtoken:jjwt-api:0.11.5")
  // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
  // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")

  // https://mvnrepository.com/artifact/org.springframework/spring-messaging implementation("org.springframework:spring-messaging")
  // implementation("com.h2database:h2")
	// testImplementation("org.springframework.security:spring-security-test")
  implementation("org.springframework.boot:spring-boot-starter-websocket")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
