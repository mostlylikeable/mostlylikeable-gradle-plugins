pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "mostlylikeable.java") {
                useModule("mostlylikeable.java:mostlylikeable-java")
            }
//            if(requested.id.id == "com.example.local-plugin") {
//                useModule("com.example:local-plugins:1.0")
//            }
        }
    }
}

include(":examples:java")

includeBuild("mostlylikeable-gradle-java") {
//    dependencySubstitution {
//        substitute(module("mostlylikeable.java:mostlylikeable-java"))
//            .using(project("../mostlylikeable-gradle-java/plugin"))
////            .using(project(":plugin"))
//    }
}

rootProject.name = "mostlylikeable-gradle-plugins"

//pluginManagement {
//    includeBuild("mostlylikeable-gradle-java")
//}
