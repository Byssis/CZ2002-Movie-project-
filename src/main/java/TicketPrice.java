import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.*;

public class TicketPrice 
{
	
	private final double GST;
	private double price;
	private int customerAge;
	private String date;	//to read current date
	private Type type;
	private CinemaType ctype;
	private double childprice;
	private double adultprice;
	private double seniorprice;
	private double threeD;
	private double platinum;
	private double wkendhol;
	
	/**
	 * Constructor for TicketPrice
	 * @param age: age of customer
	 * @param t : Type of movie 
	 * @param ctype : Cinema type
	 */
	public TicketPrice(int age,Type t,CinemaType ctype){
		Price price = Storage.getPrice();
		customerAge = age;
		this.type = t;
		this.ctype = ctype;
		this.childprice = price.getChildPrice();
		this.adultprice = price.getAdultPrice();
		this.seniorprice = price.getSeniorPrice();
		this.threeD = price.getThreeD();
		this.platinum = price.getPlatinum();
		this.wkendhol = price.getWkendHol();
		this.GST = price.getGST();
	}
	
	/**
	 * check if current date is weekend
	 * @return true if weekend, else false
	 */
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
	
	/**
	 * check if current date is public holiday
	 * @return true if holiday, else false
	 */
	private boolean isPublicHol(){
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		date = dateFormatter.format(now);
		ArrayList<String> hols = Storage.getHolidays();						//read in public holidays
		if(hols.contains(date)){
			return true;
		}else{
			return false;
		}
	}
		
	
	/**
	 * Return the price of the movie ticket inclusive of GST
	 * @return double price
	 */
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
