# Note-App

This app demonstrates a modern approach to Android development by leveraging **Kotlin, Hilt, LiveData, MMVM Architecture** and other powerful tools and libraries. The design choices prioritize **usability, maintainability, and scalability**, ensuring a smooth experience for both developers and users.

This project is implemented entirely in Kotlin and follows modern Android development practices to provide a smooth and efficient user experience. Below are the key features and technologies used in the app:

### # Key Features:</u>
**Note Management:** The app allows users to create, update, and delete personal notes. It integrates with a Room database to persist data.

**Search Functionality:** Search through your notes to quickly find the information you're looking for.

**Swipe-to-Delete:** A swipe gesture is enabled on each note item in the RecyclerView, allowing users to delete notes with a simple swipe action, making it easier to manage their notes.

**Undo Option:** A feature designed to enhance the user experience by providing an easy way to revert accidental changes.

**User Interface:** Clean and intuitive UI, built with ViewBinding to provide an efficient way to work with views and reduce boilerplate code.
<br><br>

### # Technologies and Components:

**MVVM Architecture:** The app follows the Model-View-ViewModel (MVVM) architecture to ensure a clean separation of concerns, making the codebase more maintainable and testable.

**Repository Pattern:** The Repository pattern is used to abstract data access operations, making it easier to manage and test. 

**Fragments & Navigation Components:** The app uses Fragments to manage various UI components and Navigation Components to handle navigation between them, with a modular, flexible UI that makes it easier to implement navigation patterns.

**Hilt for Dependency Injection:** Hilt is used to manage dependencies in the app, following best practices to create a scalable and testable codebase.

**RecyclerView & ListAdapter:** RecyclerView is used to display large datasets efficiently, with ListAdapter and DiffUtil helping to manage item changes and updates seamlessly.

**LiveData:** Used to handle and observe data changes in a lifecycle-conscious way, allowing the UI to automatically update in response to data changes.

**Kotlin Coroutines:** Used for asynchronous programming, allowing for non-blocking operations and improving the app's responsiveness.

**Extension Functions:** These functions help keep the codebase concise and readable by extending existing classes with new functionality.
<br><br>

### # Architecture & Design Choices:
**Single Activity Architecture:** A modern Android architecture where all fragments are hosted within a single activity, improving navigation consistency.

**Room Database:** The app uses Room as the local database solution for storing notes, with migrations in place to handle future changes to the schema.

**Testable Code:** The use of Hilt and MVVM ensures that the app's components are loosely coupled and can be easily tested.
