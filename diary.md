# Task 1 -
- Change minSdkVersion to 21
- Ensure the code compiles.
- Run it on a version lower than 31 and check if PIP works
- When it fails/crashes then put those apis that crash under a runtime build version check.
- Since the PIP was introduced only on Android 8 (API Level 26) run it on a lower version (pre 26) device or emulator check the behaviour.
- Introduce a Handler abstract class to group the API breaking statements for better maintainability. Why abstract because it can give default implementation when the specific implementation is not supported on a particular platform.
- Added a customizable dialog to be shown when the Android version is below Oreo since PIP is not supported and so to inform the user that the app functionality is limited.
# Task 2 -
- Used an inmemory repository to store the timer details as it is not required to persist when app exits,  
and used it to store timestamp when the coroutine cancels or completes. Also viewmodel restores the timer when initializing.
# Task 3 - 
- Added two tests for the ViewModel just to demostrate how can the LiveData with Coroutines in a ViewModel be tested.
- Added Hilt based dependency Injection so that ViewModel, Repository and Datasource are injected.