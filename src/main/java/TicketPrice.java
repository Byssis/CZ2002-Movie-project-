import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.*;

public class TicketPrice 
{
	private static final double GST = 1.07;
	private double price;
	private int customerAge;
	private String date;
	//private MovieStatus movie;
	private Type type;
	private CinemaType ctype;
	private double childprice;
	private double adultprice;
	private double seniorprice;
	private double threeD;
	private double platinum;
	private double wkendhol;
	
	{
		try{
			File price = new File("/Users/Public/prices.txt");						//read in ticket prices
			BufferedReader getInfo = new BufferedReader(new FileReader(price));
			String s = getInfo.readLine();
			childprice = Double.parseDouble(s);
			String t = getInfo.readLine();
			adultprice = Double.parseDouble(t);
			String q = getInfo.readLine();
			seniorprice = Double.parseDouble(q);
			String r = getInfo.readLine();
			threeD = Double.parseDouble(r);
			String y = getInfo.readLine();
			platinum = Double.parseDouble(y);
			String z = getInfo.readLine();
			wkendhol = Double.parseDouble(z);
			getInfo.close();
		}
		catch(Exception e){
				System.out.println("File not Found!");
		}}
	public TicketPrice(int age,Type movie,CinemaType ctype){
		customerAge = age;
		this.type = movie;
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
		ArrayList<String> hols = Storage.getHolidays();						//read in public holidays
		if(hols.contains(date)){
			return true;
		}else{
			return false;
		}
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
			price += wkendhol;
		}
		if(type==Type.ThreeD){
			price += threeD;
		}
		if(ctype==CinemaType.PLATINUM){
			price += platinum;
		}
		return price * this.GST;
	}
	
	
	
}
