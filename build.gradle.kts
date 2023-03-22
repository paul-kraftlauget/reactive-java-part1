plugins {
	java
}

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	val reactorVersion = "3.5.4"
	testImplementation("io.projectreactor:reactor-test:$reactorVersion")
	implementation("io.projectreactor:reactor-tools:$reactorVersion")
	implementation("org.apache.commons:commons-lang3:3.0")
}
