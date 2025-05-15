//Name: Farbod 
//Last name : Jalilfar
//Student Id : 501152985


import java.util.ArrayList;
import java.util.Scanner;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try{
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int indexF = 0;
					int indexT = 0;

					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						indexF = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						indexT = scanner.nextInt() + 1;
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					for(int i = indexF; i < indexT; i++){
						AudioContent content = store.getContent(i);
						if (content == null)
							System.out.println("Content Not Found in Store");
						else{
							try{
								mylibrary.download(content);
							}catch(contentAlreadyDownloaded e){
								System.out.println(e.getMessage());
							}
						}
					}
										
				}
				else if (action.equalsIgnoreCase("SEARCHP"))
				{
					String target = "";
					System.out.print("Partial Word: ");
					if (scanner.hasNext())
					{
						target = scanner.nextLine().trim();
					}
					store.searchPartially(target);
				}
	
				else if (action.equalsIgnoreCase("DOWNLOADA")) 
				{
					System.out.print("Artist: ");
					String artist = scanner.nextLine();

					ArrayList<Integer> artistContents = store.artistContents(artist);

					for(Integer index: artistContents){
						AudioContent content = store.getContent(index + 1);
						if (content == null)
							System.out.println("Content Not Found in Store");
						else{
							try{
								mylibrary.download(content);
							}catch(contentAlreadyDownloaded e){
								System.out.println(e.getMessage());
							}
						}
					}				
				}
				else if (action.equalsIgnoreCase("DOWNLOADG")) 
				{
					System.out.print("Genre: ");
					String genre = scanner.nextLine();

					ArrayList<Integer> genreContents = store.genreContents(genre);

					for(Integer index: genreContents){
						AudioContent content = store.getContent(index + 1);
						if (content == null)
							System.out.println("Content Not Found in Store");
						else{
							try{
								mylibrary.download(content);
							}catch(contentAlreadyDownloaded e){
								System.out.println(e.getMessage());
							}
						}
					}				
				}


				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					System.out.print("Song Number: ");
					int songIndex = scanner.nextInt();
					mylibrary.playSong(songIndex);
					// Print error message if the song doesn't exist in the library
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
				// Print error message if the book doesn't exist in the library
					System.out.print("Audio Book Number: ");
					int bookNum = scanner.nextInt();
					mylibrary.printAudioBookTOC(bookNum);
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					System.out.print("Audio Book Number: ");
					int bookIndex = scanner.nextInt();
					System.out.print("Chapter: ");
					int bookChapter = scanner.nextInt();
					mylibrary.playAudioBook(bookIndex, bookChapter);
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();
					mylibrary.playPlaylist(title);
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();
					System.out.print("Content Number: ");
					int number = scanner.nextInt();

					mylibrary.playPlaylist(title, number);
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					System.out.print("Library Song #: ");
					int number = scanner.nextInt();

					mylibrary.deleteSong(number);
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();
					mylibrary.makePlaylist(title);
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();

					mylibrary.printPlaylist(title);
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					String type = scanner.nextLine();
					System.out.print("Library Content #: ");
					int index = scanner.nextInt();

					mylibrary.addContentToPlaylist(type, index, title);
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.nextLine();
					System.out.print("Playlist Content #: ");
					int index = scanner.nextInt();

					mylibrary.delContentFromPlaylist(index, title);
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}
				// search
				else if (action.equalsIgnoreCase("SEARCH")) // sort songs by length
				{
					System.out.print("Title: ");
					String title = scanner.nextLine();

					store.search(title);
				}
				else if (action.equalsIgnoreCase("SEARCHA")) // sort songs by length
				{
					System.out.print("Artist: ");
					String artist = scanner.nextLine();

					store.searchA(artist);
				}
				else if (action.equalsIgnoreCase("SEARCHG")) // sort songs by length
				{
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					String genre = scanner.nextLine();

					store.searchG(genre);
				}

			}catch(AudioContentNotFoundException e){
				System.out.println(e.getMessage());
			}catch(contentAlreadyDownloaded e){
				System.out.println(e.getMessage());
			}catch(ChapterNotFoundException e){
				System.out.println(e.getMessage());
			}catch(playListAlreadyExistsException e){
				System.out.println(e.getMessage());
			}catch(playListNotFoundException e){
				System.out.println(e.getMessage());
			}

			System.out.print("\n>");
		}
		scanner.close();
	}
}
