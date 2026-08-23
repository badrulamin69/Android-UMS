# Walkthrough - Fixing Gradle Sync Error (Missing 'lottie')

I have resolved the Gradle sync error where the `lottie` property was not found in the version catalog.

## Changes Made

### Dependency Configuration
- Updated [libs.versions.toml](file:///F:/Android-fr-bc/SMSandroid/gradle/libs.versions.toml) to include the missing library definitions.
- Added `lottie`, `constraintlayout`, and `coordinatorlayout` to the Version Catalog as they were used in the app's `build.gradle` but not defined in the catalog.

#### [MODIFY] [libs.versions.toml](file:///F:/Android-fr-bc/SMSandroid/gradle/libs.versions.toml)
Added the following versions:
```toml
lottie = "6.7.1"
constraintlayout = "2.2.2"
coordinatorlayout = "1.3.0"
```
And the following library definitions:
```toml
lottie = { group = "com.airbnb.android", name = "lottie", version.ref = "lottie" }
constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
coordinatorlayout = { group = "androidx.coordinatorlayout", name = "coordinatorlayout", version.ref = "coordinatorlayout" }
```

## Verification Results

### Automated Tests
- Ran `gradle_sync` which finished successfully.

> [!TIP]
> Always ensure that any library referenced via `libs.something` in your `build.gradle` files is explicitly defined in the `gradle/libs.versions.toml` file under the `[libraries]` section.
