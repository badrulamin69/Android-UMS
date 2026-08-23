# Implementation Plan - Fix Android Login Failure

The goal is to identify and fix the root cause of the login failure in the SMS Android application when connecting to the Spring Boot backend.

## User Review Required

> [!IMPORTANT]
> The backend `SecurityConfig` uses a constructor for `DaoAuthenticationProvider` that may not be available in all Spring Security versions. I will refactor this to use standard setters.
>
> I will also ensure the test user `student` is always re-seeded with the correct password `123456` during development to avoid issues with stale database records.

## Proposed Changes

### [Backend] EliteUniversity Spring Boot

#### [MODIFY] [SecurityConfig.java](file:///F:/Android-fr-bc/smsspring-bc/src/main/java/com/brilliantsofts/EliteUniversity/config/SecurityConfig.java)
- Refactor `authenticationProvider` bean to use `setUserDetailsService` and `setPasswordEncoder` explicitly.

#### [MODIFY] [DataInitializer.java](file:///F:/Android-fr-bc/smsspring-bc/src/main/java/com/brilliantsofts/EliteUniversity/config/DataInitializer.java)
- Update `ensureAndroidTestStudent` to ensure the password is reset to `123456` (or the environment variable value) even if the user already exists, ensuring the development credentials are always valid.

### [Android] SMS App

#### [MODIFY] [ApiClient.java](file:///F:/Android-fr-bc/SMSandroid/app/src/main/java/com/brilliantsoft/sms/network/ApiClient.java)
- Enable `HttpLoggingInterceptor.Level.BODY` to allow for easier debugging of network requests in Logcat.

## Verification Plan

### Automated Tests
- Deploy the Android app.
- Attempt login with `student` / `123456`.
- Inspect Logcat for successful `POST /api/auth/login` and a 200 OK response containing a JWT.

### Manual Verification
- Verify that the app navigates to `MainActivity` upon successful login.
- Verify that "Login Successful" Toast appears.
