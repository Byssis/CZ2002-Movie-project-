/*
 * Created by Albin on 2017-10-12.
*/
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Driver {
static List<Movie> movies = new ArrayList<Movie>();
    
public static void main(String args[]){
    
		int childprice=0;
		int adultprice=0;
		int seniorprice=0;
		try{
			PrintWriter newprice = new PrintWriter("/Users/Public/test.txt");
			newprice.println("7");
			newprice.println("9");
			newprice.println("8");
			newprice.close();		//default ticket prices
		}
		catch(Exception e){
			System.out.println("Error!");
		}
		
	    String title;
	    String director;
	    int duration;
	    int castno;
	    String[] cast;
	    int option;
	    Type type;
	    Date startDate;
	    Date endDate;
	    int year;
	    int month; 
	    int date;			//for new movie insertion
	    String a;
	    
    	Scanner sc = new Scanner(System.in);
    	String[] users = {"user123", "123"}; 		//will be implemented with file IO
    	String user,password;
    	while(true){
    	System.out.println("Enter username");
    	user = sc.next();
    	System.out.println("Enter password");
    	password = sc.next();
    	if(user.equals(users[0])&&password.equals(users[1])){
    		System.out.println("Successfully logged in");
    		break;
    	}else{
    		System.out.println("Logged in failed! Try again!");
    	}}
    	int choice;
    	while(true){
    	System.out.println("What would you like to do?");
    	System.out.println("1 - Configure System Settings");
    	System.out.println("2 - Enter New Movie");
    	System.out.println("3 - Update Movie Details");
    	System.out.println("4 - List Top 5 Ranking Movies");
    	System.out.println("5 - Quit");
    	choice = sc.nextInt();
    	switch(choice){
    		case 1: 
    		{	System.out.println("1 - Change Ticket Prices");
    			System.out.println("2 - Add/Remove Holidays");
    			if(sc.nextInt()==1){
    				
    				try{
    					File price = new File("/Users/Public/test.txt");
    					BufferedReader getInfo = new BufferedReader(new FileReader(price));
    					String p = getInfo.readLine();
    					childprice = Integer.parseInt(p);
    					String q = getInfo.readLine();
    					adultprice = Integer.parseInt(q);
    					String r = getInfo.readLine();
    					seniorprice = Integer.parseInt(r);
    				}
    				catch(Exception e){
    						System.out.println("File not Found!");
    				}
    				
    				System.out.println("Current prices are Child: " + childprice + " Adult: " + adultprice + " Senior: " + seniorprice);
    				System.out.println("Enter the new ticket prices in the format \"child price <space> adult price <space> senior price\" e.g. 7 9 8");
    				childprice = sc.nextInt();
    				adultprice = sc.nextInt();
    				seniorprice = sc.nextInt();
    				try{
    					PrintWriter newprice = new PrintWriter("/Users/Public/test.txt");
    					newprice.println(childprice);
    					newprice.println(adultprice);
    					newprice.println(seniorprice);
    					newprice.close();
    				}
    				catch(Exception e){
    					System.out.println("Error!");
    				}    				
    				System.out.println("Ticket prices successfully updated");
    			}else{
    				//edit holidays
    			}
    				
    			break;}				//Change ticket price 
    		case 2:	{
    			System.out.println("Enter Movie Title: ");
    			a = sc.nextLine();
    			title = sc.nextLine();
    			System.out.println("Enter Director Name: ");
    			director = sc.nextLine();
    			System.out.println("Enter Movie Duration (in minutes): ");
    			duration = sc.nextInt();
    			System.out.println("Enter the number of cast members ");
    			castno = sc.nextInt();
    			cast = new String[castno];
    			System.out.println("Enter cast name");
    			a = sc.nextLine();
    			for(int x=0;x<castno;x++){
    				cast[x] = sc.nextLine();
      			}
    			System.out.println("Enter Movie Type");
    			System.out.println("1 - 3D\n2 - Digital");
    			option = sc.nextInt();
    			if(option==1){
    				type = Type.TREED;
    			}else{
    				type = Type.NORMAL;
    			}
    			System.out.println("Enter the start date in the format DD MM YYYY");
    			date = sc.nextInt();
    			month = sc.nextInt();
    			year = sc.nextInt();
    			startDate = new Date(year,month,date);
    			System.out.println("Enter the end date in the format DD MM YYYY");
    			date = sc.nextInt();
    			month = sc.nextInt();
    			year = sc.nextInt();
    			endDate = new Date(year,month,date);
    			Movie test = new Movie(title,director,duration,cast,type,startDate,endDate);    			
    			System.out.println(test.toString());
    			break;}		//enter movie
    		case 3: break;				//update the details of the movies or remove the movie
    		case 4:	break;				//order by sales or rating
    		case 5: break;				//quit
    	}
    		if(choice==5){
    			break;			//Quit program
    		}
    	
    	}
  
    	
	    
}


    	
}

	
    
    	
    


