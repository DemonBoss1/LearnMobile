pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    repositories {
        mavenCentral()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "First App"
include(":app")
