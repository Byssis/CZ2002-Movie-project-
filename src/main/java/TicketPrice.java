import java.util.*;
import java.text.*;

public class TicketPrice 
{
	private double price;
	private int customerAge;
	private String date;
	private Movie movie;
 
	public TicketPrice(int age){
		customerAge = age;
	}
	
	private boolean isWeekend(){
		Date now = new Date(); 
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(now);
	    int day = calendar.get(Calendar.DAY_OF_WEEK);
	    if(day==1 || day==6 || day==7){	//1 is Sunday, 6 is Friday, 7 is Saturday.
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	private boolean isPublicHol(){
		Date now = new Date();
		 SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d");
		date = dateFormatter.format(now);
		String[] holidays = {"January 1" , "April 14" , "August 9"};		//change to IO because admin can change holidays
		for(int x=0;x<holidays.length;x++){
			if(date.equals(holidays[x])){
				return true;
			}
		}
		return false;
	}
	
	
	
	public double getPrice(){
		return price;
	}
	
	
	
}
