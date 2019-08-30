

public class Song implements Comparable<Song>{
	
	//variables
	private int id;
	String title;
	int likes;
	
	//final static variables
	static final int MAX_ID = 9999;
	static final int MAX_TITLE_LENGTH = 80;
	//default constructor
	public Song(){
		this.id = 0;
		this.title = null;
		this.likes = -1;
	}//end of Song
	
	//constructor nubmer1
	public Song(int id, String title, int likes){
		
		if(id >= 1 && id <= MAX_ID){
			this. id = id;
		}else{
			this.id = 0;
		}//end if
		
		if(title.length() <= MAX_TITLE_LENGTH){
			this.title = title;
		}else{
			this.title = null;
		}//end if
		
		this.likes = likes;
	}//end of Song
	
	//get/set Methods
	public void setId(int id){
		
		if(id >= 1 && id <= MAX_ID){
			this. id = id;
		}else{
			this.id = 0;
		}
		
	}//end of setId
	
	public void setTitle(String title){
		
		if(title.length() <= MAX_TITLE_LENGTH){
			this.title = title;
		}else{
			this.title = null;
		}
		
	}//end of setTitle
	
	public void setLikes(int likes){
		this.likes = likes;
	}//end of setLikes
	
	public int getId(){
		return id;
	}//end of getId
	
	public String getTitle(){
		return title;
	}//end of getTitle
	
	public int getLikes(){
		return likes;
	}//end of getLikes
	
	//interface Method
	
	public int compareTo(Song song){
		int result;
		//comparing likes
		if(this.likes < song.getLikes()){
			return -1;
		}else if(this.likes == song.getLikes()){
			result = 0;
		}else{
			return 1;
		}
		
		//if likes are equals compare titles
		if(result != 0){
			return result;
		}else{
			if(song.getTitle().compareTo(getTitle()) < 0)
				return -1;
			else if(song.getTitle().compareTo(getTitle()) > 0)
				return 1;
			return 0;
		}

	}//end of compareTo
	
	//void method that prints song info
	public void printSong(){
		
		System.out.println("ID: " + getId() );
		System.out.println("Song title: " + getTitle() );
		System.out.println("Likes: " + getLikes() );
				
	}//end of printSong
	
	public boolean isValidSong(){
		
		if(getId() <= 0 || getTitle().length() <= 0 || getLikes() < 0){
			return false;
		}
		return true;
		
	}//end of isValidSong
	
}//end of Song
