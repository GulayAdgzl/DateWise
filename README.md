# Date Wise - Personalized Event Reminder App 📅

Date Wise is a personalized event reminder application that ensures users never miss important occasions. Users can easily add their friends' birthdays, anniversaries, and other special events, receiving timely reminders to keep their loved ones in mind.

## App Features 🚀

- **Personalized Notifications:** Receive customized reminders for upcoming events, making sure you never forget to celebrate with friends and family.

- **Event Management:** Easily add, edit, or remove events from your list, keeping your calendar up-to-date.

- **Custom Reminders:** Set personalized reminders for each event, so you're always prepared to celebrate.

- **Direct Greetings:** Send heartfelt messages and greetings to your friends directly from the app, making their day even more special.

## Architecture 🏛️

Date Wise follows the Model-View-ViewModel (MVVM) architecture, providing a structured and scalable design for various components.

### Package Structure 📦

- **Core (Base):** Contains common classes, extensions, utilities, and base classes shared throughout the app.

- **Data:** Manages event data using Room database, including entities, DAOs (Data Access Objects), and repository patterns. Focuses on data processing without including business logic.

- **UI (User Interface):** Organized by features, this package represents the event reminder functionality. It includes fragments, view models, domains, mappers, and UI models.

- **Di (Dependency Injection):** Manages dependency injection, enhancing modularity and testability, and located outside the core package for better visibility.

- **Ui-Component (User Interface Components):** Contains reusable view components used across different parts of the app, promoting code reusability and easier maintenance.

## Built With 🛠️

| Library             | Description                                                        |
| ----------------- | ------------------------------------------------------------------ |
| [Kotlin](https://kotlinlang.org/) | The first-class and official programming language for Android development. |
| [Coroutines](https://developer.android.com/kotlin/coroutines) | For handling asynchronous operations and more. |
| [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) | A collection of libraries for designing robust, testable, and maintainable apps. |
| ➡️ [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) | Data objects that notify views when the underlying database changes.
| ➡️ [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)| Stores UI-related data that survives configuration changes.
| ➡️ [ViewBinding](https://developer.android.com/topic/libraries/view-binding) | Generates a binding class for each XML layout file, simplifying view interactions.
| ➡️ [Room](https://developer.android.com/training/data-storage/room) | SQLite object mapping library for local data storage.
| [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) | Facilitates scheduling background tasks and managing complex work chains.

With Date Wise, you'll stay connected with your loved ones, never missing an opportunity to make their special days even more memorable. Enjoy seamless event management and personalized notifications in this user-friendly app. 🎉

## Get Started 🚀

To get started with Date Wise, please follow the installation instructions in our [wiki](link-to-wiki). Happy event planning!

 


