# Netflix Mobile (Android)

A Netflix-style Android client built with Kotlin and Jetpack Compose.

## Features
- Home feed with a featured banner and horizontal rows
- Video detail page for movies and TV shows (with episode list)
- Local favorites stored in Room
- "My Netflix" tab with profile and recently viewed
- Full-screen video playback with Media3 ExoPlayer

## Tech stack
Kotlin · Jetpack Compose (Material 3) · MVVM + Repository · Hilt · Retrofit + Gson · Room · Navigation Compose · Coil · Media3 ExoPlayer

## Architecture
UI (Compose) → ViewModel (`StateFlow<UiState>`) → Repository → Retrofit / Room.
State flows down, events flow up.

## Run
1. Start the mock backend (see the `netflix_backend` repository) on port 8080.
2. Open this project in Android Studio and run the `app` configuration on an emulator.
   The app reaches the backend at `http://10.0.2.2:8080/` (the emulator's alias for the host's localhost).

Poster images are loaded from the internet, so the emulator needs network access.
