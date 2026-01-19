# dev-intensive-2019

Android/Kotlin training application organized as a sequence of tasks implemented on separate branches.

Recommended starting point: **branch `hometask_4`** (most complete implementation).

## What this project demonstrates (hometask_4)

- Kotlin-based Android application structure (Gradle, app module)
- MVVM-style UI state with AndroidX Lifecycle: ViewModel + LiveData
- Simple local persistence with SharedPreferences
- Custom UI widgets implemented in Kotlin:
  - AspectRatioImageView (AppCompatImageView with aspect-ratio handling)
  - CircleImageView (circular rendering via Android graphics: Bitmap/Drawable; includes VectorDrawable handling)
- Test dependencies configured (JUnit + Espresso + AndroidX Test)

## Tech stack (configured/used)

- Kotlin 1.4.10
- Android Gradle Plugin 4.0.1
- compileSdk/targetSdk 30, minSdk 23
- AndroidX: AppCompat, Core KTX, Lifecycle (ViewModel/LiveData)
- UI: ConstraintLayout, Material Components, Palette KTX
- Testing: JUnit4, Espresso, AndroidX Test

## Branches

- hometask_1 — initial skeleton / setup
- hometask_2 — early tasks and baseline app structure
- hometask_3 — intermediate tasks (incremental functionality)
- hometask_4 — most complete version (recommended)

## Quickstart (Android Studio)

    git clone https://github.com/vmlinuz/dev-intensive-2019.git
    cd dev-intensive-2019
    git checkout hometask_4

Open the project in Android Studio, let Gradle sync, then run on an emulator or device.

## Notes

- Repository sources: root build uses google() + mavenCentral(); jcenter() was removed because it is deprecated.
- No Gradle wrapper is committed yet (no ./gradlew). If you add one, pin a Gradle version compatible with AGP 4.0.1 (typically Gradle 6.x).

## License

No license is currently specified.
