import java.util.Comparator;

public class PQ<T> {
	
	
	//Heap 
	private T[] heap;
	private int size;
	private int capacity;
	
	//Comparator
	protected Comparator<T> cmp;
	
	
	//Constructor
	@SuppressWarnings("unchecked")
	public PQ(int capacity, Comparator<T> cmp){
		if (capacity < 1) throw new IllegalArgumentException();
		
		this.heap = (T []) new Object[capacity+1];
        this.size = 0;
        this.cmp = cmp;
        this.capacity = capacity;
	}//end of constructor
	
	//insert
	public void insert(T object) {
        // Ensure object is not null
        if (object == null) throw new IllegalArgumentException();
        
       // System.out.println(heap.length);
      //resizing heap when its on 75% used
        if(size > (this.capacity*75)/100) resize(2*this.capacity);
        
        
        // Check available space
        if (size == heap.length - 1) throw new IllegalStateException();
        
        
        // Place object at the next available position
        heap[++size] = object;
        // Let the newly added object swim
        swim(size);
    }
	
	@SuppressWarnings("unchecked")
	public void resize(int capacity){
		
		//creating a copy of heap
		T[] copy = (T []) new Object[capacity+1];
		this.capacity *= 2;
		this.capacity++;
		for(int i = 1; i <= this.size; i++)
			copy[i] = this.heap[i];
		this.heap = copy;
		
		
	}//end of resize
	
	//getMax
	public T getMax() {
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        T object = heap[1];
        // Replace root object with the one at rightmost leaf
        if (size > 1) heap[1] = heap[size];
        // Dispose the rightmost leaf
        heap[size--] = null;
        // Sink the new root element
        sink(1);
        // Return the object removed
        return object;
    }
	
	@SuppressWarnings("unchecked")
	//remove
	public T remove(int id){
		
		// Ensure not empty
        if (size == 0) throw new IllegalStateException();
		
		Song test = new Song();
		int position = 1;
		
		for(int i = 1; i <= size; i++){
			test = (Song) heap[i];
			if(test.getId() == id){
				System.out.println("Found");
				heap[position+1] = heap[size];
				break;
			}else{
				position = i;
			}
		}
		
		if(position == -1)
			return null;
			
		heap[size--] = null;
		
		sink(position+1);
		
		return (T) test;
		
	}//end of remove
	
	/**
     * Shift up.
     */
    private void swim(int i) {
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result = cmp.compare(heap[i], heap[p]);  //compare parent with child
            if (result <= 0) return;    //if child <= parent return
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    }
    
    /**
     * Shift down.
     */
    private void sink(int i){
        int left = 2*i, right = left+1, max = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max = cmp.compare(heap[left], heap[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (cmp.compare(heap[i], heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }
    
    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
    	return size;
    }//end of size
    
    public T max(){
    	// Ensure not empty
        if (size == 0) throw new IllegalStateException();
        T object = heap[1];
        return object;
        
    }//end of max
    
    /*public void print(int k) {
       
    	for(int i = k; i >= 1; i--){
    		Song s = (Song) heap[i];
    		s.printSong();
    		System.out.println();
    	}
        	
    }*/
    
    
	
}//end of PQ
