import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.*;

public class TicketPrice 
{
	int baseprice = 5;
	private double price;
	private int customerAge;
	private String date;
	private Movie movie;
	private Type type;
	private CinemaType ctype;
	private int childprice;
	private int adultprice;
	private int seniorprice;
	
	
	{
	try{
	File tprice = new File("/Users/Public/test.txt");
	BufferedReader getInfo = new BufferedReader(new FileReader(tprice));
	String p = getInfo.readLine();
	childprice = Integer.parseInt(p);
	String q = getInfo.readLine();
	adultprice = Integer.parseInt(q);
	String r = getInfo.readLine();
	seniorprice = Integer.parseInt(r);
	}catch(Exception e){
		System.out.println("Error!");
	}}
	public TicketPrice(int age,Movie movie,CinemaType ctype){
		customerAge = age;
		this.movie = movie;
		type = movie.getType();
		this.ctype = ctype;
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
		if(customerAge<=12){
			price = childprice;
		}else if(customerAge>=65){
			price = seniorprice;
		}else{
			price = adultprice;
		}
		if(isWeekend()||isPublicHol()){
			price += 2;
		}
		if(movie.getType()==Type.TREED){
			price += 2;
		}
		if(ctype==CinemaType.PLATINUM){
			price += 2;
		}
		return price;
	}
	
	
	
}
