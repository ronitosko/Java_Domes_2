
import java.util.Comparator;

final class SongComparator implements Comparator<Song>{
	
	public int compare(Song s1, Song s2){
		
		int result;
		//comparing likes
		if(s1.getLikes() < s2.getLikes())
			result =  1;
		else if(s1.getLikes() > s2.getLikes())
			result = -1;
		else
			result = 0;
		
		
		//if likes are equals compare titles
		if(result != 0){
			return result;
		}else{
		
			if(s1.getTitle().compareTo(s2.getTitle()) < 0)
				result = -1;
			else if(s1.getTitle().compareTo(s2.getTitle()) > 0)
				result =  1;
			else
				result = 0;
		}
		return result;
		
	}

}//end of class
