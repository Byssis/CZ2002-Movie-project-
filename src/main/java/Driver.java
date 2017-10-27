/**
 * Created by Albin on 2017-10-12.
*/
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.text.*;

public class Driver {
static List<Movie> movies = new ArrayList<Movie>();
    
public static boolean isValidDate(String inDate) {						//check if the date passed in is a valid one
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }



public static void main(String args[]){
		
		
		ArrayList<String> hols = new ArrayList<String>();						//read in public holidays
		try{
			File pubhol = new File("/Users/Public/pubhol.txt");
			BufferedReader getInfo = new BufferedReader(new FileReader(pubhol));
			String temp = getInfo.readLine();
			while(temp!=null){
				hols.add(temp);
				temp = getInfo.readLine();
			}
			getInfo.close();
		}
		catch(Exception e){
			System.out.println("File not found!");
		}
		
		
		int childprice=0;
		int adultprice=0;
		int seniorprice=0;
		
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
    	
    	ArrayList<String> useracc = new ArrayList<String>();
    	
    	try{
			File acc = new File("/Users/Public/useracc.txt");							//read in account info
			BufferedReader getInfo = new BufferedReader(new FileReader(acc));
			String temp = getInfo.readLine();
			while(temp!=null){
				useracc.add(temp);
				temp = getInfo.readLine();
			}
			getInfo.close();
    	}
		catch(Exception e){
				System.out.println("File not Found!");
		}
    	
    	String user,password;
    	while(true){
    	System.out.println("Enter username");
    	user = sc.next();
    	System.out.println("Enter password");
    	password = sc.next();
    	if(useracc.contains(user) && useracc.contains(password)){
    		if(useracc.indexOf(user)== useracc.indexOf(password)-1){
    				System.out.println("Login successful!");
    				break;
    		}
    	}
    	else{
    		System.out.println("Incorrect username or password! Please try again");
    	}
    	}
    
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
    					File price = new File("/Users/Public/test.txt");						//read in ticket prices
    					BufferedReader getInfo = new BufferedReader(new FileReader(price));
    					String p = getInfo.readLine();
    					childprice = Integer.parseInt(p);
    					String q = getInfo.readLine();
    					adultprice = Integer.parseInt(q);
    					String r = getInfo.readLine();
    					seniorprice = Integer.parseInt(r);
    					getInfo.close();
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
    				
    				System.out.println("Here are the current holidays:");
    				for(int x=0;x<hols.size();x++){
    					System.out.println(hols.get(x));
    				}
    				int opt;
    				while(true){
					System.out.println("What would you like to do?");
					System.out.println("1 - Remove Holiday");
					System.out.println("2 - Add Holiday");
					System.out.println("3 - Back to Main Menu");
					opt = sc.nextInt();
					if(opt==1){//remove holiday
						a = sc.nextLine();
						String holtodelete;
						while(true){
							System.out.println("Enter the new holiday in the format Day of Week, Month Day. eg. Monday, May 1");
							holtodelete = sc.nextLine();
							if(!hols.contains(holtodelete)){
								System.out.println("Holiday does not exist! Try again!");
							}else{
								break;
							}	
						}
						hols.remove(holtodelete);
						try{
	    					PrintWriter newhols = new PrintWriter("/Users/Public/pubhol.txt");
	    					for(int x=0;x<hols.size();x++){
	    						newhols.println(hols.get(x));
	    					}
	    					newhols.close();
	    					System.out.println("Holiday successfully removed!");
						}
	    				catch(Exception e){
	    					System.out.println("Error!");
	    				}
						
					}
					else if(opt==2){
						a = sc.nextLine();
						String newhol;
						while(true){
							System.out.println("Enter the new holiday in the format Day of Week, Month Day. eg. Monday, May 1");
							newhol = sc.nextLine();
							if(hols.contains(newhol)){
								System.out.println("Holiday alreadys exists! Try again!");
							}else{
								if(isValidDate(newhol)){
								break;}else{
								System.out.println("Not a valid date! Try again!");
								}
							}
							}	
							hols.add(newhol);
						try{
	    					PrintWriter newhols = new PrintWriter("/Users/Public/pubhol.txt");
	    					for(int x=0;x<hols.size();x++){
	    						newhols.println(hols.get(x));
	    					}
	    					newhols.close();
	    					System.out.println("Holiday successfully added!");
						}
	    				catch(Exception e){
	    					System.out.println("Error!");
	    				}
						
						
					}else if(opt==3){
						break;
					}else{
						System.out.println("Invalid option!");
					}
    				}
					
    			}	break;
					}	//Change ticket price 
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
    			System.out.println("1 - 3D\n2 - Normal\n3 - Blockbuster");
    			option = sc.nextInt();
    			if(option==1){
    				type = Type.TREED;
    			}else if(option==2){
    				type = Type.NORMAL;
    			}else{
    				type = Type.BLOCKBUSTER;
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
    			List<Movie> movies = new ArrayList();
    			movies = Storage.getMovieList();
    			movies.add(newmovie);
    			Storage.writeMovieList(movies);
    			System.out.println(movies);
    			break;}		//enter movie

    		case 3: break; //update the details of the movies or remove the movie
    		case 4:	break;				//order by sales or rating
    		case 5:{ 
    			System.out.println("Goodbye!");
    			break;}				//quit
    		default: {
    			System.out.println("Invalid option!");
    			break;
    		}
    	}
    		if(choice==5){
    			break;			//Quit program
    		}
    	
    	}	    
}
}

	
    
    	
    

