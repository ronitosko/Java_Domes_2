

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dynamic_Median {
	
	public static void main(String[] args){
		
		
		//user input
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the number of k max songs to appear: ");
		int user = input.nextInt();
		
		//reading txt file
		BufferedReader reader;
		String path = args[0];
		String line = null;
		
		//need to tokenize string
		String[] lineTokens;
		
		//creating 2 PQ the first will contain values that are greater than the current median
		//and the other will contain values that are less than the current median
		PQ<Song> minHeap = new PQ<Song>(10, new MinLikesComparator());
		PQ<Song> maxHeap = new PQ<Song>(10, new SongComparator());
		int median = 0;
		
		int totalSongs = 0;
		String songTitle = "";
		String medianSong;
		
		
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
					
					//System.out.println(likes);
					totalSongs++;
					if(song.getLikes() < median){
						maxHeap.insert(song);
					}else{
						minHeap.insert(song);
					}
					
					if(maxHeap.size() - maxHeap.size() == 2){
						minHeap.insert(maxHeap.getMax());
					}else if(minHeap.size() - maxHeap.size() == 2){
						maxHeap.insert(minHeap.getMax());
					}//end if
					
					//find median
					if(minHeap.size() >= maxHeap.size()){
						median = minHeap.max().getLikes();
						medianSong = minHeap.max().getTitle();
					}else{
						median = maxHeap.max().getLikes();
						medianSong = maxHeap.max().getTitle();
					}
					
					
					if(totalSongs % 5 == 0){
						System.out.println("Median: " + median + " likes, achieved by song: " + medianSong);
					}
					line = reader.readLine();
					if(totalSongs == user){
						break;
					}//end if
					
					
				}//end while
				
			}catch(IOException e){
				System.out.println("Error while reading file");
			}//end try
			
			
			
		}catch(FileNotFoundException e){
			System.out.println("File doesnt exist! :(");
		}//end try
		
		//System.out.println(median);
		
		
		//closing scanner
		input.close();
	}//end of main
	

}//end of class
