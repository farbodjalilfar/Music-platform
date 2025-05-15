//Name: Farbod 
//Last name : Jalilfar
//Student Id : 501152985


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content)
	{

		String contentType = content.getType();
		if(contentType.equalsIgnoreCase("song")){
			Song newContent = (Song) content;
			if(songs.contains(newContent)){
				throw new contentAlreadyDownloaded("Song " + newContent.getTitle() + " already downloaded");
			}
			songs.add(newContent);
			System.out.println("Song " + newContent.getTitle() + " Added to Library");
		}else if(contentType.equalsIgnoreCase("audioBook")){
			AudioBook newContent = (AudioBook) content;
			if(audiobooks.contains(newContent)){
				throw new contentAlreadyDownloaded("AudioBook " + newContent.getTitle() + " already downloaded");
			}
			audiobooks.add(newContent);
			System.out.println("AudioBook " + newContent.getTitle() + " Added to Library");
		}

	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for(int i = 0; i < audiobooks.size(); i++){
			System.out.print(i + 1 + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for(int i = 0; i < playlists.size(); i++){
			System.out.println(i + 1 + ". " + playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		ArrayList<String> artists = new ArrayList<>();
		for(Song aSong: songs){
			if(!artists.contains(aSong.getArtist())){
				artists.add(aSong.getArtist());
			}
		}
		
		for(int i = 0; i < artists.size(); i++){
			System.out.println(i + 1 + ". " + artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		Song deleting = songs.get(index - 1);
		if(index > 0 && songs.size() >= index){
			songs.remove(index - 1);
		}else{
			throw new AudioContentNotFoundException("Song was not found!");
		}

		for(Playlist playlist: playlists){
			if(playlist.getContent().indexOf(deleting) != -1){
				playlist.deleteContent(playlist.getContent().indexOf(deleting) + 1);
			}
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		SongYearComparator songYearComparator = new SongYearComparator();
		Collections.sort(songs, songYearComparator);
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator
	{

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			Song s1 = (Song) o1;
			Song s2 = (Song) o2;
			return Integer.compare(s1.getYear(), s2.getYear());
		}
		
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		// Use Collections.sort() 
		SongLengthComparator songLengthComparator = new SongLengthComparator();
		Collections.sort(songs, songLengthComparator);
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator
	{

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			Song s1 = (Song) o1;
			Song s2 = (Song) o2;
			return Integer.compare(s1.getLength(), s2.getLength());
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || songs.size() < index)
		{
			throw new AudioContentNotFoundException("Song not found!");
		}
		songs.get(index-1).play();
		return;
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		if(audiobooks.size() < index){
			throw new AudioContentNotFoundException("AudioBook Not Found");
		}
		if(audiobooks.get(index - 1).getChapters().size() < chapter){
			throw new ChapterNotFoundException("Chapter doesn't exist!");
		}
		audiobooks.get(index - 1).selectChapter(chapter);
		audiobooks.get(index - 1).play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if(audiobooks.size() < index){
			throw new AudioContentNotFoundException("Book does not exist!");
		}
		audiobooks.get(index - 1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		Playlist newPlaylist = new Playlist(title);
		if(playlists.contains(newPlaylist)){
			throw new playListAlreadyExistsException("Playlist " + title + " Already Exists");
		}

		playlists.add(newPlaylist);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		Playlist playlist = new Playlist(title);
		if(playlists.indexOf(playlist) != -1){
			playlist = playlists.get(playlists.indexOf(playlist));
		}else{
			throw new playListNotFoundException("PlayList was not found!");
		}

		playlist.printContents();
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		Playlist playlist = new Playlist(playlistTitle);
		if(playlists.indexOf(playlist) != -1){
			playlist = playlists.get(playlists.indexOf(playlist));
		}else{
			throw new playListNotFoundException("PlayList was not found!");
		}

		playlist.playAll();
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{

		Playlist playlist = new Playlist(playlistTitle);
		if(playlists.indexOf(playlist) != -1){
			playlist = playlists.get(playlists.indexOf(playlist));
		}else{
			throw new playListNotFoundException("PlayList was not found!");
		}

		playlist.play(indexInPL);;

	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		Playlist playlist = new Playlist(playlistTitle);
		if(playlists.indexOf(playlist) != -1){
			playlist = playlists.get(playlists.indexOf(playlist));
		}else{
			throw new playListNotFoundException("PlayList was not found!");
		}

		if(type.equalsIgnoreCase("Song")){
			if(index < 1 || songs.size() < index){
				throw new AudioContentNotFoundException("Song does not exist!");
			}
			AudioContent newContent = songs.get(index - 1);
			playlist.addContent(newContent); 
		}else if(type.equalsIgnoreCase("AudioBook")){
			if(index < 1 || audiobooks.size() < index){
				throw new AudioContentNotFoundException("AudioBook does not exist!");
			}
			AudioContent newContent = audiobooks.get(index - 1);
			playlist.addContent(newContent); 
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		Playlist playlist = new Playlist(title);
		if(playlists.indexOf(playlist) != -1){
			playlist = playlists.get(playlists.indexOf(playlist));
		}else{
			throw new playListNotFoundException("PlayList was not found!");
		}

		playlist.deleteContent(index);
	}
	
}

class AudioContentNotFoundException extends RuntimeException {
    public AudioContentNotFoundException(){}
    public AudioContentNotFoundException(String message){
        super(message);
    }
}

class contentAlreadyDownloaded extends RuntimeException {
    public contentAlreadyDownloaded(){}
    public contentAlreadyDownloaded(String message){
        super(message);
    }
}

class ChapterNotFoundException extends RuntimeException {
    public ChapterNotFoundException(){}
    public ChapterNotFoundException(String message){
        super(message);
    }
}

class playListAlreadyExistsException extends RuntimeException {
    public playListAlreadyExistsException(){}
    public playListAlreadyExistsException(String message){
        super(message);
    }
}

class playListNotFoundException extends RuntimeException {
    public playListNotFoundException(){}
    public playListNotFoundException(String message){
        super(message);
    }
}