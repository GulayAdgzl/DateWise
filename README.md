
# Date Wise

My app idea is a personalized event reminder application where users can add their friends' birthdays and other special occasions. The app will send timely notifications to users, reminding them of the upcoming events of their friends and loved ones. With this user-friendly and thoughtful app, people will never miss an important birthday or celebration again. The app will allow users to easily manage their event list, set custom reminders, and even send personalized greetings to their friends directly from the platform. Whether it's a birthday, anniversary, or any other significant milestone, our app will help users stay connected and make their loved ones feel cherished on their special days.

# Architecture
Your application aims to be a personalized event reminder app, allowing users to add their friends' birthdays and other special occasions. Users will receive timely notifications for upcoming events, ensuring they never miss important celebrations. The app will utilize the Model-View-ViewModel (MVVM) architecture, offering a clean and scalable design for different components.

 ## Package Structure:
 ### Core (Base):

🟠This package will include all common classes used throughout the application.

🟠It will contain extensions, deciders, utilities, and base classes that can be shared.

### Data:
🟢The Data package will handle event data, including response models, data sources, and API methods.

🟢This package should focus solely on data processing and avoid including business logic.

### UI (User Interface):

🟣 Each feature will be represented as a separate module, and this package will specifically represent the event reminder functionality.

🟣 It includes the following components:

   🟤Fragments: Representing different screens and user interfaces for adding, managing, and viewing event details.
      
   🟤🟤View Models: Responsible for managing business logic and data manipulation for UI components.
      
   🟤🟤🟤Domains: Defining domain models specific to the event reminder feature.
      
   🟤🟤🟤🟤Mappers: Includes classes used for data mapping between different layers, for instance, mapping response models to domain models.
   
   🟤🟤🟤🟤🟤UI Models: Special models used for displaying data in the UI.
### Di (Dependency Injection):
   ◍This package manages dependency injection and is preferably located outside the core package for better visibility.
   
  ◍It facilitates providing and managing dependencies throughout the app, enhancing modularity and testability.
  
### Ui-Component (User Interface Components):

✤ This package contains common view components used in different parts of the app.

✤ By centralizing these components, you can reuse them across multiple features, promoting code reusability and easier maintenance.


The application will consist of a single root activity responsible for managing different fragments using the Navigation Component. Each feature, such as the event reminder functionality, will be organized under the "UI" package following the MVVM architecture. The "Data" package will handle data management, while the "Core" package will contain shared classes and utilities. The "Di" package will manage dependency injection, and the "Ui-Component" package will host reusable view components.

By structuring your application in this manner and utilizing the MVVM architecture, you will achieve a well-organized and maintainable codebase. This approach will also make it easier to add new features and improve the application over time.



# Build With  🛠

| Library             | comment                                                                |
| ----------------- | ------------------------------------------------------------------ |
| [Kotlin](https://kotlinlang.org/) | First class and official programming language for Android development. |
| [Coroutines](https://tr.search.yahoo.com/search?fr=mcafee&type=E210TR91105G0&p=Coroutines) | For asynchronous and more.|
| [Android Architecture Components](https://tr.search.yahoo.com/search?fr=mcafee&type=E210TR91105G0&p=Android+Architecture+Components) |  Collection of libraries that help you design robust, testable, and maintainable apps. |
|➡️[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) |Data objects that notify views when the underlying database changes.
 ➡️ [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)|  Stores UI-related data that isn't destroyed on UI changes.
 ➡️ [ViewBinding](https://developer.android.com/topic/libraries/view-binding) | Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
 ➡️ [Room](https://developer.android.com/topic/libraries/architecture/room) | SQLite object mapping library.


