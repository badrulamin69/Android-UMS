# Bug Fix: Static Data and Incomplete Data Fetching

Analysis revealed that multiple activities either don't bind backend data to the UI or lack the necessary student information in the session to fetch relevant data. The `MainActivity` specifically has a placeholder comment where data binding should occur.

## Proposed Changes

### [Authentication & Session]

#### [MODIFY] [LoginActivity.java](file:///F:/Android-UMS/app/src/main/java/com/brilliantsoft/sms/ui/auth/LoginActivity.java)
- After successful login, if the user role is `STUDENT`, call `apiService.getMe()` to fetch detailed student information.
- Save `studentId`, `studentCode`, and `programName` into `SessionManager` before navigating to `MainActivity`.

### [Main Dashboard]

#### [MODIFY] [MainActivity.java](file:///F:/Android-UMS/app/src/main/java/com/brilliantsoft/sms/ui/main/MainActivity.java)
- Implement data binding in `loadDashboardData()`.
- Extract and display attendance percentage and today's schedule from the `getStudentDashboard()` response.
- Ensure `tvStudentName` and `tvStudentId` are updated with the latest data from `SessionManager` or the dashboard response.

### [Profile]

#### [MODIFY] [ProfileActivity.java](file:///F:/Android-UMS/app/src/main/java/com/brilliantsoft/sms/ui/profile/ProfileActivity.java)
- Add loading indicator handling (if applicable) and error toasts.
- Verify all fields from `StudentProfileResponse` are correctly mapped to UI components.

## Verification Plan

### Automated Tests
- Build the project to ensure no syntax errors in data binding.
- Run `app:assembleDebug` to verify the build.

### Manual Verification
- Deploy the app and perform a login.
- Verify that `MainActivity` shows real student name and ID instead of "John Doe".
- Navigate to Profile and verify all fields are populated from the backend.
- Check that the dashboard correctly reflects attendance and schedule (if data is available in the mock/real backend).
