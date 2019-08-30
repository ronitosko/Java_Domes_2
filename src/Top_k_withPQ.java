

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Top_k_withPQ {

	public static void main(String[] args) {
		
		//user input
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the number of k max songs to appear: ");
		int user = input.nextInt();
		int checker = 0;
		
		
		//reading txt file
		BufferedReader reader;
		String path = args[0];
		String line = null;
		
		//need to tokenize string
		String[] lineTokens;
		
		//creating a table with the max number of Songs
		PQ<Song> songs = new PQ<Song>(2, new SongComparator());
		
		int i = 0; // table index
		int totalSongs = 0;
		String songTitle = "";
		
		
		try{

			reader = new BufferedReader(new InputStreamReader( new FileInputStream(path) ) ); //reading txt file with BufferedReader
			
			try{
				
				line = reader.readLine(); //read first line
				while(line != null){
					
					int totalTokens = new StringTokenizer(line, " ").countTokens();
					//reading lines and splitting them into tokens
					lineTokens = line.split(" "); //tokenize string wthen you fine space between two words
					Song song = new Song(); //creating new Song
					//adding song info 
					song.setId( Integer.parseInt(lineTokens[0]) );
					int j = 1;
					while(totalTokens-1 > j){
		
						songTitle += lineTokens[j].trim() + " ";
						j++;
					}
					//System.out.println(songTitle);
					song.setTitle(songTitle); //set Title
					songTitle = ""; //for the next Song
					song.setLikes( Integer.parseInt(lineTokens[totalTokens-1]) );
					
					if(song.isValidSong()){
						
						if(totalSongs < user){
							songs.insert(song);
							totalSongs++;
						}else{
							//check maxheap root with the rest elements
							if(songs.max().compareTo(song) == -1){
								songs.getMax();
								songs.insert(song);
							}//end if
							
						}//end else
						checker++;
						
					}else{
						System.out.println("This song is not valid!");
						song.printSong();
						System.out.println();
					}//end if/else
					
					line = reader.readLine();
				}//end while
				
			}catch(IOException e){
				System.out.println("Error while reading file");
			}//end try
			
			
		}catch(FileNotFoundException e){
			System.out.println("File doesnt exist! :(");
		}//end try
		
		//songs.print(user);
		
		System.out.println("Top Songs are");
		System.out.println("-----------------------------------------");
		int finalSize = songs.size();
		if(finalSize <= checker){
			
			String[] titles = new String[user];
			for(i = 0; i < user; i++){
				titles[i] = songs.getMax().getTitle();
			}
			for(i = user-1; i >= 0; i--){
				System.out.println("Song title: " + titles[i]);
			}
			
		}else{
			String[] titles = new String[checker];
			for(i = 0; i < checker; i++){
				titles[i] = songs.getMax().getTitle();
			}
			for(i = checker-1; i >= checker - 3; i--){
				System.out.println("Song title: " + titles[i]);
			}
		}
		
		
		
		//closing input
		input.close();
	}//end main

}//end Class
