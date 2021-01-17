package logic;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteCsv {
	
	  private WriteCsv() {
		    throw new IllegalStateException("Utility class");
		  }
	
	static int check=0;
	public static List<Object> dataCsv(String month, List<Object> array) {

	    boolean found=false;
	    if(check==0){
	    array.add(month.substring(1, 9));
	    array.add(Integer.valueOf(1));
	    check=1;
	    
	    }
	    else {
	    	int i;
	    	for(i=0; i<array.size();i=i+2) {
	    		
	    	if(array.get(i).equals(month.substring(1,9))) {
	    		found=true;
	    		
	    	}
	    	if(found){
	    		int occurrences = (Integer) array.get(i+1);
	    		occurrences=occurrences+1;
	    		array.set(i+1, occurrences);
	    		
	    		return array;
	    	}
	    		
	    	}
	    	//if found==false
	    	array.add(month.substring(1, 9));
	    	array.add(Integer.valueOf(0));
	    	
	    }
	   	 
		return array;
	  
	}
	
	public static void write(List<Object> array,FileWriter fileWriter) {
		
		for(int i=0; i<array.size();i=i+2) {
			try {
				fileWriter.append(String.valueOf(array.get(i)));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(array.get(i+1)));
				
				fileWriter.append("\n");
			} catch (IOException e) {
				e.fillInStackTrace();
			}finally {
				try {
					fileWriter.flush();
				}
				catch(Exception e){
					e.fillInStackTrace();
					
				}
			
			
		}
		
		
	}
	
	}
}

