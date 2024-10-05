# Song Search App

**Song Search App** is a mobile application that allows users to search through a collection of songs by **title**, **lyrics (content)**, or **author**. The app provides a seamless experience where users can either type their search queries or use voice recognition to search for songs. The application is built with modern technologies such as **Jetpack Compose** for UI and **Kotlin Coroutines** for asynchronous operations.

## Features

- **Search by Title, Lyrics, and Author**: The app allows users to search for songs by matching the title, lyrics, or author name. All searches are combined in a single query to provide comprehensive results.
- **Voice Search**: Users can initiate a voice search using the microphone icon to dictate their query. The search results are displayed as if the query was typed.
- **Text Search**: A `TextField` allows users to type their search query manually. The app provides a clear icon (`Clear` button) to reset the search.
- **Real-time Search**: The app performs searches as users type, providing real-time feedback and results.
- **Search Results**: The results are displayed dynamically based on the query. The app avoids duplicate results by combining searches on different fields (title, content, author).
- **Persistence**: All songs are loaded at app startup and stored in a state that allows for fast searching and filtering.

## Technologies Used

- **Kotlin**: The primary language used for developing the app.
- **Jetpack Compose**: For building a declarative and reactive UI.
- **Kotlin Coroutines**: To handle asynchronous tasks such as fetching data and performing searches efficiently.
- **Hilt (Dependency Injection)**: To manage dependencies in a scalable and modular manner.
- **StateFlow**: To manage the state of the app's data in a reactive way.

## Architecture

The app follows a **Clean Architecture** pattern, which separates the concerns of the app into different layers:

1. **Presentation Layer**: This layer contains the UI code using Jetpack Compose. It communicates with the `ViewModel` to get data and perform actions such as searching.

2. **Domain Layer**: Contains the core business logic of the app, including `use cases` that are responsible for performing searches by title, content, and author.

3. **Data Layer**: Manages the data, such as fetching all songs and filtering them based on the user's search. This layer is connected to the domain layer via repositories.

## Key Components

### ViewModel

The `SongViewModel` is the central component of the presentation layer, managing the state of the songs and handling the search logic.

- **search(query: String)**: This method allows for simultaneous searching by title, content, and author. It uses coroutines to perform these searches in parallel and combines the results.

### Use Cases

- **GetAllSongsUseCase**: Retrieves all songs at app startup.
- **SearchSongByTitleUseCase**: Searches for songs that match the provided title.
- **SearchSongByContentUseCase**: Searches for songs that match the provided content or lyrics.
- **SearchSongByAuthorUseCase**: Searches for songs that match the provided author's name.

### UI Components

- **SearchBar**: A composable `TextField` where users can type their search query. It also includes a voice search button and a clear button for easy interaction.
- **VoiceSearchButton**: A button that allows users to trigger a voice search using the device's speech recognition system.

## Installation

To run the app locally:

1. Clone the repository:
   ```
   git clone https://github.com/codemossaka/songbookApp.git
   ```

2. Open the project in **Android Studio**.

3. Make sure you have an Android device or emulator configured.

4. Build and run the app on your device.

## Usage

1. **Search by typing**: Enter any part of a song title, lyrics, or author's name in the search bar. The results will update in real-time.

2. **Voice search**: Tap the microphone icon, speak your query, and the app will display results matching your spoken input.

3. **Clear search**: Tap the clear button (`X` icon) to reset the search.

## Future Improvements

- **Add Sorting and Filtering**: Implement options for users to sort or filter the search results (e.g., by most popular, by recently added).
- **Song Details**: Display detailed information about each song, including album, release date, and lyrics.
- **Favorites**: Allow users to favorite songs for easier access later.

## Contributing

If you would like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENCE) file for details.

---

Thank you for using Song Search App! If you encounter any issues or have feature suggestions, feel free to open an issue or contribute.