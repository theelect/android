
This project aggregates data from field agents.

## The Elect Android App
- App is split into 3 layers
 - Presentation Layer: App UI lives in this layer, this layer is built using MVVM architecture with android data binding. All the App UI is based on Fragments. All the UI code (Fragments and Activities) can be found in the `ui` folder. The UI layer includes  `com.tonyecoleelection.android.ui.auth.AuthActivity` which contains UI for registration, login, password reset in package `com.tonyecoleelection.android.ui.auth.fragments` and `com.tonyecoleelection.android.ui.main.MainActivity` which contains UI for admin and ward cordinator dashboard in package `com.tonyecoleelection.android.ui.main.fragments`.

 - Domain Layer: This contains the use cases/database interactors that provide abstractions over how data is managed and fetched in the app. All Usecases are stored in appropriately named folders.

 - Data Layer: The data layer utilizes ROOM and retrofit with Rxjava to make asyncronous network calls and persist data in the phone storage. Data fetched here feeds directly into the Usecases and the UI.

## How to build

- [ ] Clone this repository into a folder of your project's name `git clone https://github.com/theelect/android.git MY_PROJECT`. Or if you're copying the folder, don't forget hidden files!
- [ ] Make sure you have an internet connection and are on at least version 3.2 of Android Studio
- [ ] Import into Android Studio
- [ ] Please uninstall any old versions of the app before running the new one (No SQLite Database migration script is provided)
- [ ] Run a gradle build
- [ ] Push a build to your phone/emulator

## License

    Copyright (C) 2017

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
