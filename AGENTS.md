# OdeToCarStar Development Notes

## Setup

- Set `API_KEY` in `local.properties` before building. This value is injected via `gradleLocalProperties` into `buildConfigField`.
- `versionCode` is computed from `git rev-parse --short HEAD`.

## Build Commands

```bash
# Gradle Wrapper
./gradlew assembleDebug

# Run Tests
./gradlew test

# Run Android Tests
./gradlew androidTest
```

## Architecture

- Single-module Gradle project (`:app`)
- Jetpack Compose UI (`buildFeatures.compose = true`)
- Hilt DI with providers defined in `core/di/`
- Repository pattern: `CarRepositoryImpl` → `CarApi` (Retrofit) → RapidAPI
- Room database for offline caching (README TODO)

## Testing Notes

- Hilt testing: `androidTestImplementation(libs.hilt.android.testing)`
- Mocking: uses MockK and MockWebServer
- Test fixtures rely on the API; consider caching or mocking for stable runs

## Gotchas

- `local.properties` is gitignored—don't check in `API_KEY`
- Free API tier: 5,000 requests/month; caching is recommended
- `minSdk = 28`, `targetSdk = 35`
