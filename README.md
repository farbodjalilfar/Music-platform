# Audio Content Library Management System

## Overview
This project is a simulation of a simple text-based music and audiobook application, similar to Apple Music. It allows users to manage, store, and play audio content such as songs, audiobooks, and playlists. The application also supports downloading content from an online store, searching for content, and managing playlists.

## Features
- **Audio Content Management**: Manage songs, audiobooks, and playlists.
- **Download Content**: Download songs and audiobooks from the store.
- **Search Functionality**: Search for content by title, artist, genre, or partial matches.
- **Play Content**: Play songs, audiobooks (specific chapters), and playlists.
- **Sort Songs**: Sort songs by year, title, or length.
- **Playlist Management**: Create, delete, and manage playlists.
- **Error Handling**: Custom exceptions for handling errors like content not found, duplicate downloads, and invalid operations.

## File Structure
- **`AudioContent.java`**: Abstract class representing common properties of all audio content.
- **`Song.java`**: Class representing a song, extending `AudioContent`.
- **`AudioBook.java`**: Class representing an audiobook, extending `AudioContent`.
- **`Playlist.java`**: Class for managing playlists containing audio content.
- **`Library.java`**: Class for managing the user's library of songs, audiobooks, and playlists.
- **`AudioContentStore.java`**: Class simulating an online store for audio content.
- **`MyAudioUI.java`**: Main class providing a text-based user interface for interacting with the application.
- **`store.txt`**: File containing the initial data for songs and audiobooks in the store.
- **`LICENSE`**: MIT License for the project.

## How to Run

To run the application:

```bash
java MyAudioUI
```

---

## Commands

The application supports the following commands:

- `STORE` : List all content in the store.
- `SONGS` : List all songs in the library.
- `BOOKS` : List all audiobooks in the library.
- `ARTISTS` : List all unique artists in the library.
- `PLAYLISTS` : List all playlists in the library.
- `DOWNLOAD` : Download content from the store by index.
- `DOWNLOADA` : Download all content by a specific artist.
- `DOWNLOADG` : Download all content of a specific genre.
- `PLAYSONG` : Play a song by its index in the library.
- `PLAYBOOK` : Play a specific chapter of an audiobook.
- `MAKEPL` : Create a new playlist.
- `ADDTOPL` : Add content to a playlist.
- `PRINTPL` : Print the contents of a playlist.
- `PLAYALLPL` : Play all content in a playlist.
- `SORTBYYEAR` : Sort songs by year.
- `SORTBYNAME` : Sort songs alphabetically by title.
- `SORTBYLENGTH` : Sort songs by length.
- `SEARCH` : Search for content by title.
- `SEARCHA` : Search for content by artist.
- `SEARCHG` : Search for content by genre.
- `SEARCHP` : Search for content by partial matches.

---

## Example Usage

### 1. List all songs in the library:

```plaintext
> SONGS
```

---

### 2. Download a song from the store:

```plaintext
> DOWNLOAD
From Store Content #: 1
To Store Content #: 1
```

---

### 3. Play a song:

```plaintext
> PLAYSONG
Song Number: 1
```

---

### 4. Create a playlist:

```plaintext
> MAKEPL
Playlist Title: My Playlist
```

---

### 5. Add a song to the playlist:

```plaintext
> ADDTOPL
Playlist Title: My Playlist
Content Type [SONG, PODCAST, AUDIOBOOK]: SONG
Library Content #: 1
```

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Author

- **Name**: Farbod Jalilfar  
- **Student ID**: 501152985
