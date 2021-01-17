package logic;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SysCall {
	
	public static final String PROJ_NAME = "C:\\Users\\valen\\Desktop\\falcon";
	public static final String CMD = "git -C "; 
	
	  private SysCall() {
		    throw new IllegalStateException("Utility class");
		  }
	  
	public static void log(String key, List<Object> array) {
	
	Process logGit=null;
	String s = null;
	String month ="";
	
	try{
              
		File path = new File(PROJ_NAME);
		logGit = Runtime.getRuntime().exec(CMD+path +" log -1 --pretty=format:\"%cs\" --grep=" + key );
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(logGit.getInputStream()));
        // read the output from the command
        //return date
        while ((s = stdInput.readLine()) != null ) {
        	   String[] res = Arrays.copyOfRange(s.split("-"), 0, 2);
        	   month=Arrays.toString(res);
        	   WriteCsv.dataCsv(month,array);
           }
  
	}
  catch(Exception ex)
  {
        if(logGit!=null)
        {
              logGit.destroy();
        }
        ex.fillInStackTrace();
        System.exit(-1);
  }
	

}
	
	
	public static List<String> getDatesBetween(String initialDate, String finalDate) {
		String adj = "-01";
		LocalDate startDate = LocalDate.parse(initialDate.substring(0,7) + adj);
		LocalDate endDate = LocalDate.parse(finalDate.substring(0,7) + adj);
		List<String> dates = new ArrayList<>();
		dates.add(initialDate.substring(0,7));
		LocalDate datePlus = startDate;
		while(!datePlus.equals(endDate)) {
			datePlus = datePlus.plusMonths(1);
			String dateToAdd = datePlus.toString().substring(0,7);
			dates.add(dateToAdd);
		}
		return dates;
	}
	
	public static String firstCommit(String path) {
		String date = null;
		try {
			String pathComplete = path;
			Process p = Runtime.getRuntime().exec(CMD + pathComplete + " log --reverse --pretty=format:%cs");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			date = stdInput.readLine();
		} catch (IOException e) {
            Thread.currentThread().interrupt();
            System.exit(-1);
		}
		return date;
	}
	
	public static String lastCommit(String path) {
		String date = null;
		try {
			//First line represent the last commit date
			String pathComplete = path;
			Process p = Runtime.getRuntime().exec(CMD + pathComplete + " log --pretty=format:%cs -1");
			p.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			date = stdInput.readLine();
		} catch (Exception e) {
            Thread.currentThread().interrupt();
            System.exit(-1);
		}
		return date;
	}
	

}

