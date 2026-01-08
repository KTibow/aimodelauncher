AI Mode Launcher
----------------

An Android utility app that instantly opens Google AI Mode (udm=50) for a typed query. Launch, type, submit, and the browser opens straight to the AI-mode search.

## Build & run (local/CI)
- Requirements: JDK 17+, Android SDK Platform 34/35, Build-tools 34.0.0/35.0.0, platform-tools.
- Build: `./gradlew clean :app:assembleDebug` (done in CI).
- Output: `app/build/outputs/apk/debug/app-debug.apk`.

## Devcontainer
- Installs Java, Bazel, Gradle, Android cmdline tools.
- Pre-installs Android platforms 33/34/35 and build-tools 33.0.2/34.0.0/35.0.0.
- On container start, licenses are accepted and platform-tools + platforms 34/35 + build-tools 34.0.0/35.0.0 are ensured via postCreateCommand.

## CI
- GitHub Actions workflow: `.github/workflows/android-ci.yml`.
- Steps: checkout, Java 17, cmdline tools download, accept licenses, install SDK 34/35 + build-tools 34.0.0/35.0.0, assembleDebug with Gradle 8.13 + AGP 8.13.2.

## App behavior
- Shows a single input and button on launch.
- On submit (button or IME search), opens the default browser to `https://www.google.com/search?q=<encoded>&udm=50`.
