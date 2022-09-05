# EmCoded-Android
Code editing app that Em coded

How to Use (MVP)
------------------------------
1. When the user runs the app, they will be directed to a code editor screen
2. The code editor will display the syntax for the "Hello World" Python in Android-style grayed-out hint font
3. Using the built-in device keyboard, the user should type the code into the editor
  - NOTE: When the user enters text into the editor, the font will be colored black
4. After typing the line of code, press the button to "Run Code"
5. The app will use Volley behind the scenes to send an API PUT request to https://eval-backend.emmy-sharp.repl.co/exec
6. If the user has typed the "Hello World" syntax correctly, the console will display the API response in JSON format
  - NOTE: The "Run Code" button can be a little bit finicky and may require double taps in order to display web response
7. To clear the console, press the "Clear" button

NOTE: The "Save" button is a placeholder for future functionality, allowing the user to save their work


Future Improvements & Ideas
--------------------------------
Save Ability
- Online: when the user presses the "Save" button, their work is synced to a cloud server
- Offline: the user's work is cached and saved locally to the Android device

Multiple Language Support
- Add a dropdown box or other UI element that allows the user to select which programming language the wish to use
- Right now Python is the default

MVC Model
- Most logic is done through MainActivity.java
- I already built a separate AppController.java in the Android Studio project; however, the app currently does not use it.
