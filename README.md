# News App

The News App is a Kotlin-based project designed to demonstrate modern Android development skills using **Jetpack Compose** and **Kotlin/Compose Multiplatform**. The application is architected following **Clean Architecture** principles, ensuring a robust, maintainable, and scalable codebase.

## Features

- **Multiplatform Support:** The app is built with Kotlin/Compose Multiplatform in mind, enabling code sharing across Android and other platforms.
- **Modern UI with Jetpack Compose:** Utilizes Jetpack Compose for building declarative UIs with less code and higher efficiency.
- **SOLID Principles:** The project adheres to SOLID principles through the use of Clean Architecture, ensuring high cohesion and low coupling.
- **API Integration:** Fetches live news data using RESTful APIs.
- **Dependency Injection:** Manages dependencies efficiently with Koin, promoting testability and modularity.
- **Date and Time Handling:** Cross-platform safe date and time calculations using kotlinx-datetime.
- **Image Loading:** Efficiently loads and displays images from URLs using Coil.

## Libraries Used

The following libraries and tools are integral to the project:

1. **[Ktor](https://github.com/ktorio/ktor):** A robust framework for making asynchronous network API calls, ensuring efficient data retrieval and handling.
2. **[Koin](https://github.com/InsertKoinIO/koin):** A lightweight dependency injection framework, simplifying the management of dependencies across the application.
3. **[kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime):** Provides multiplatform support for date and time operations, ensuring consistent behavior across different platforms.
4. **[Coil](https://github.com/coil-kt/coil):** An image loading library for Android backed by Kotlin Coroutines, optimized for performance and ease of use, allowing seamless loading of images from URLs.

## Architecture

The app is structured using **[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)**, a layered architecture approach that promotes separation of concerns and increases testability. This architecture ensures that the core business logic is independent of the user interface, making the application more flexible and easier to maintain.

### Layers:
- **Domain Layer:** Contains the business logic and entities. This layer is independent of any framework or tool.
- **Data Layer:** Responsible for data retrieval and storage. It interacts with the domain layer through repository interfaces.
- **Presentation Layer:** Handles the UI and user interaction logic using Jetpack Compose and ViewModels.

## Getting Started

To run the project locally, ensure you have the following prerequisites installed:

- **JDK 11** or higher
- **Android Studio Arctic Fox** or newer
- **Kotlin 1.5** or higher

Clone the repository and open it in Android Studio. Use the standard Gradle build system to compile and run the application on an emulator or device.

## Contributing

Contributions are welcome! If you find any issues or have ideas for improvement, feel free to submit a pull request or open an issue.

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
