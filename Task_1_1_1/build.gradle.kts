group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    java
    jacoco
}



tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}




jacoco {
    toolVersion = "0.8.12"
}


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks{
    test {
        useJUnitPlatform()
    }
}



tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.8.toBigDecimal()
            }
        }
    }
}
