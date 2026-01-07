Android build instructions

A minimal Android app is included under the `app/` module and an instrumentation test is present (`app/src/androidTest`).

Build locally (devcontainer)

1. Ensure Android SDK command-line tools and Java are installed in the container.
2. Use Java 21 for the Gradle runtime and toolchain when running Gradle:

   export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64

3. Build the debug and test APKs:

   env JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew :app:assembleDebug :app:assembleDebugAndroidTest

4. The APKs will be at:

   - `app/build/outputs/apk/debug/app-debug.apk`
   - `app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk`

CI (GitHub Actions)

A workflow is provided at `.github/workflows/android-build.yml` which builds the debug and androidTest APKs and uploads them as artifacts on push/PR to `develop` or `main`.
