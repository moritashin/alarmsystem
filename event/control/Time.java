package event.control;


import  java.util.Date;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.*;  
import java.text.SimpleDateFormat;  

	  
	public class Time {  
	    /*时间比大小*/
		 public static String getCurrentTime(){  
			 
			 Date currentTime = new Date();  
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		        String dateString = formatter.format(currentTime);  
		        return dateString;  
		    }  
	    public static int timeCompare(String t1,String t2){  
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	        Calendar c1=Calendar.getInstance();  
	        Calendar c2=Calendar.getInstance();  
	        try {  
	            c1.setTime(formatter.parse(t1));  
	            c2.setTime(formatter.parse(t2));  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        }  
	        int result=c1.compareTo(c2);  
	        return result;  
	    }  
	/*   public static void main(String [] args) throws ParseException{
	    	Time tc = new Time();
	    	Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-06-11 16:55");
			Date afterDate = new Date(date .getTime() + 300000);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		    String dateString = formatter.format(afterDate);  
		    System.out.println( dateString);
				System.out.println(tc.timeCompare(tc.getCurrentTime(), dateString));
			
	    	
	    	//int t=tc.timeCompare(tc.getCurrentTime(), "2008-01-01 01:00:00");
	    	//System.out.println(t);
	    	//System.out.println(tc.getCurrentTime());
	    	
	    }*/
	    
	  
	}  
	

