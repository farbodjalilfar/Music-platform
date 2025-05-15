//Name: Farbod 
//Last name : Jalilfar
//Student Id : 501152985


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents;
		
		// The contents are being searched using hash maps.
		private Map<String, Integer> titleMap = new HashMap<>();
		private Map<String, ArrayList<Integer>> artistsMap = new HashMap<>();
		private Map<String, ArrayList<Integer>> genreMap = new HashMap<>();
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();

			//All of the contents are added to the ArrayList located above.
			addContents();
			
			ArrayList<Integer> genre;
			ArrayList<Integer> artist;

			for(int i = 0; i < contents.size(); i++){
				//Update titles map 
				AudioContent content = contents.get(i);
				titleMap.put(content.getTitle(), i);
				
				if(content instanceof Song){
					Song newSong = (Song) content;
					String genreString = newSong.getGenre().toString();

					genre = genreMap.get(genreString);
					if(Objects.isNull(genre)){
						genre = new ArrayList<>();
					}

					genre.add(i);
					genreMap.put(genreString, genre);

					artist = artistsMap.get(newSong.getArtist());
					if(Objects.isNull(artist)){
						artist = new ArrayList<>();
					}
					artist.add(i);
					artistsMap.put(newSong.getArtist(), artist);
				}else if(content instanceof AudioBook){
					AudioBook newBook = (AudioBook) content;

					artist = artistsMap.get(newBook.getAuthor());
					if(Objects.isNull(artist)){
						artist = new ArrayList<>();
					}
					artist.add(i);
					artistsMap.put(newBook.getAuthor(), artist);
				}
			}
					
		}

		private void addContents(){
			// This method opens the file store.txt and will go through it line by line
			// And it will add the songs and audiobooks to the contents arraylist.
			try{
				File storeFile = new File("store.txt");
				Scanner scn = new Scanner(storeFile);

				while(scn.hasNextLine()){

					String contentType = scn.nextLine();
					
					if(contentType.equals("SONG")){
						String id = scn.nextLine();
						String title = scn.nextLine();
						String year = scn.nextLine();
						String length = scn.nextLine();
						String artist = scn.nextLine();
						String composer = scn.nextLine();
						String genre = scn.nextLine();

						int lyricsCount = scn.nextInt();
						String lyrics = "";
						for(int i = 0; i < lyricsCount; i++){
							lyrics += scn.nextLine() + "\n";
						}

						contents.add(new Song(title, Integer.parseInt(year), id, Song.TYPENAME, title + ".mp3", Integer.parseInt(length), 
						artist, composer, Song.Genre.valueOf(genre), lyrics));
						
					}else if(contentType.equals("AUDIOBOOK")){

						
						String id = scn.nextLine();
						String title = scn.nextLine();
						int year = Integer.parseInt(scn.nextLine());
						int length = Integer.parseInt(scn.nextLine());
						String author = scn.nextLine();
						String narrator = scn.nextLine();
						
						int chapterCount = scn.nextInt();
						ArrayList<String> chapterTitles = new ArrayList<>();
						ArrayList<String> chapters = new ArrayList<>();
						scn.nextLine();
						
						for(int i = 0; i < chapterCount; i++){
							chapterTitles.add(scn.nextLine());
						}
						
						for(int i = 0; i < chapterCount; i++){
							int lineCount = scn.nextInt();
							scn.nextLine();
							String tempChapter = "";
							for(int j = 0; j < lineCount; j++){
								tempChapter += scn.nextLine() + "\n";
							}
							chapters.add(tempChapter);
						}
						

						AudioBook book = new AudioBook(title, year, id, AudioBook.TYPENAME,  "", length,
								author, narrator, chapterTitles, chapters);
						contents.add(book);
					}

				}
				scn.close();

			}catch(IOException e){
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		public boolean search(String title){
			Integer finding = titleMap.get(title);
			if(Objects.isNull(finding)){
				System.out.println("No matches for " + title);
				return(false);
			}
			System.out.print((finding + 1) + ". ");
			getContent(finding + 1).printInfo();

			return(true);
		}

		public boolean searchA(String artist){
			ArrayList<Integer> findings = artistsMap.get(artist);
			if(Objects.isNull(findings)){
				System.out.println("No matches for " + artist);
				return(false);
			}

			for(Integer index: findings){
				System.out.print((index + 1) + ". ");
				getContent(index + 1).printInfo();
				System.out.println();
			}
			return(true);
		}

		public boolean searchG(String genre){
			ArrayList<Integer> findings = genreMap.get(genre);
			if(Objects.isNull(findings)){
				System.out.println("No matches for " + genre);
				return(false);
			}

			for(Integer index: findings){
				System.out.print((index + 1) + ". ");
				getContent(index + 1).printInfo();
				System.out.println();
			}
			return(true);
		}

		public ArrayList<Integer> artistContents(String artist){
			ArrayList<Integer> res = artistsMap.get(artist);
			if(Objects.isNull(res)){
				res = new ArrayList<>();
			}
			return(res);
		}

		public ArrayList<Integer> genreContents(String genre){
			ArrayList<Integer> res = genreMap.get(genre);
			if(Objects.isNull(res)){
				res = new ArrayList<>();
			}
			return(res);
		}
				// BONUS
				public void searchPartially(String target)
				{
					{
						boolean found = false;
						for (String key: titleMap.keySet())
						{
							AudioContent content = contents.get(titleMap.get(key)-1);
							String contentString = content.getAudioFile() + Integer.toString(content.getLength()) + content.getTitle()
								+ Integer.toString(content.getYear()) + content.getId();
							if (content.getType().equals("SONG"))
							{
								Song song = (Song) content;
								contentString += song.getComposer() + song.getArtist();
								if (song.getGenre() == Song.Genre.POP) {contentString += "POP";}
								else if (song.getGenre() == Song.Genre.ROCK) {contentString += "ROCK";}
								else if (song.getGenre() == Song.Genre.JAZZ) {contentString += "JAZZ";}
								else if (song.getGenre() == Song.Genre.HIPHOP) {contentString += "HIPHOP";}
								else if (song.getGenre() == Song.Genre.RAP) {contentString += "RAP";}
								else if (song.getGenre() == Song.Genre.CLASSICAL) {contentString += "CLASSICAL";}
							}
							else if (content.getType().equals("AUDIOBOOK"))
							{
								AudioBook audiobook = (AudioBook) content;
								contentString += audiobook.getAuthor() + audiobook.getNarrator();
								for (String chapterTitle: audiobook.getChapterTitles())
								{
									contentString += chapterTitle;
								}
								for (String chapter: audiobook.getChapters())
								{
									contentString += chapter;
								}
							}
		
							if (contentString.contains(target))
							{
								found = true;
								System.out.print(titleMap.get(key) + ". ");
								contents.get(titleMap.get(key)-1).printInfo();
							}
						}
						if (found == false)
						{
							System.out.println("No matches for " + target);
						}
					}
				}
		
}
