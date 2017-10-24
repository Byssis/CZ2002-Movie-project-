/**
 * Created by Albin on 2017-10-12.
*/
import java.text.SimpleDateFormat;
import java.util.*;


public class Driver {

    public static void main(String args[]){
    
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
    		case 1: break;				//Change ticket price 
    		case 2: break;				//enter movie
    		case 3: break;				//update the details of the movies or remove the movie
    		case 4:	break;				//by sales or rating
    		case 5: break;				//quit
    	}
    		if(choice==5){
    			break;			//Quit program
    		}
    	
    	}
    
}

    
    
    	
    }

