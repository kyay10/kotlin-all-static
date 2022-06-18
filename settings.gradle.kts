rootProject.name = "kotlin-all-static"

include(":gradle-plugin")
include(":kotlin-plugin")
include(":kotlin-plugin-native")
include("prelude")
includeBuild("convention-plugins")
include("maven-plugin")
