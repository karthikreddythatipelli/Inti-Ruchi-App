# Inti Ruchi Android App

A clean Android app (Jetpack Compose) to show food varieties and subscription plans, add items to cart, and place an order.

## Features
- 4 food varieties with names and prices.
- 2 subscription plans (15 days and 30 days) with prices.
- Add any item to cart.
- View cart total.
- Place order with validation.

## Prerequisites
- Android Studio Iguana or newer.
- JDK 17.
- Android SDK 35.

## Run locally
1. Open Android Studio.
2. Click **Open** and select this project folder.
3. Let Gradle sync complete.
4. Run on emulator/device using the **Run** button.

## Publish checklist
1. Change `applicationId` in `app/build.gradle.kts`.
2. Update app icon and app name.
3. Set release signing in Android Studio.
4. Enable minify if needed and test thoroughly.
5. Build signed AAB and upload to Google Play Console.
