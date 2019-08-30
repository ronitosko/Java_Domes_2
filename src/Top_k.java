

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Top_k {
	
		public static void main(String[] args){
			
			//user input
			Scanner input = new Scanner(System.in);
			int user;
			
			//reading txt file
			BufferedReader reader;
			String path = args[0];
			String line = null;
			
			//need to tokenize string
			String[] lineTokens;
			
			//creating a table with the max number of Songs
			Song[] songsList = new Song[9999];
			
			int i = 0; // table index
			int totalSongs = 0;
			String songTitle = "";
			
			try{
				
				reader = new BufferedReader(new InputStreamReader( new FileInputStream(path) ) ); //reading txt file with BufferedReader
				try{
					
					line = reader.readLine(); //read first line
					
					while(line != null){
						int totalTokens = new StringTokenizer(line, " ").countTokens();
						//System.out.println(totalTokens);
						
						//reading lines and splitting them into tokens
						lineTokens = line.split(" "); //tokenize string wthen you fine space between two words
						Song song = new Song(); //creating new Song
						//adding song info 
						song.setId( Integer.parseInt(lineTokens[0]) );
						int j = 1;
						while(totalTokens-1 > j){
			
							songTitle += lineTokens[j] + " ";
							j++;
						}
						//System.out.println(songTitle);
						song.setTitle(songTitle); //set Title
						songTitle = ""; //for the next Song
						song.setLikes( Integer.parseInt(lineTokens[totalTokens-1]) );
						
						//System.out.println("Here");
						if(song.isValidSong()){
							songsList[i] = song;
							i++;
							totalSongs++;
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
			
			
			Song[] finalList = new Song[totalSongs];
			for(i = 0; i < totalSongs; i++){
				finalList[i] = songsList[i];
			}
			
			//System.out.println(finalList.length + "\n");
			quickSort(finalList, 0, totalSongs-1);
			
			
			/*System.out.println("---------------------------------------------------");
			for(i = 0; i < totalSongs; i++){
				finalList[i].printSong();
			}*/
			
			System.out.print("Please enter top k songs to appear(max:" + totalSongs + "):");
			user = input.nextInt();
			if(user > totalSongs){
				user = 3;
				System.out.println("There are not so many songs in the list! :(");
			}
			System.out.println("The top " + user + " songs are:");
			for(i = 0; i < user; i++)
				System.out.println(finalList[i].getTitle());
			
			
			//closing input
			input.close();
		}//end of main
		
		public static void quickSort(Song[] songsList, int p, int r){
			
			
			if(r <= p) return;
			
			int i = partition(songsList, p ,r);
			quickSort(songsList, p, i-1);
			quickSort(songsList, i+1, r);
			
			
		}//end of quickSort
		
		//pivot is a[r]
		public static int partition(Song[] list, int p, int r){
			
			int i = p-1;
			int j = r;
			Song pivot = list[r];
			
			for(;;){
				while(list[++i].compareTo(pivot) > 0){
					//System.out.println(list[i].compareTo(pivot) + "\n");
					//list[i].printSong();
					//System.out.println();
					//pivot.printSong();
				}
				while( pivot.compareTo(list[--j]) > 0 ){
					//System.out.println(pivot.compareTo(list[j]) + "\n");
					//pivot.printSong();
					//System.out.println();
					//list[j].printSong();
					if(j == p) break;
				}
				if(i >= j) break;
				exch(list, i, j);
						
			}//end for
			exch(list, i, r);
			return i;
		}//end of partition
		
		public static void exch(Song[] list, int i, int j){
			Song temp = list[i];
			list[i] = list[j];
			list[j] = temp;
		}
	
}//end of Top_k
