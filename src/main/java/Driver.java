 /* Created by Albin on 2017-10-12.
*/
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.text.*;

public class Driver {

	/**
	 * Method to check if a date is valid
	 * @param inDate : date to be checked
	 * @return true if date is valid, false if not
	 */
	public static boolean isValidDate(String inDate) {                        
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	public static void main(String args[]) {

		
		/*Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormatter.format(now);
		System.out.println(date);
		*/
	     
		ArrayList<Movie> movies5 = new ArrayList<Movie>(); 
		movies5 = Storage.getMovieList();

		//ArrayList<String> hols = Storage.getHolidays();						//read in public holidays


		UserApp userapp;
		String title;
		String director;
		String duration;
		String movieabstract;
		String option;
		String startDate;
		String endDate;
		String a;
		String cineplexname;
		String cinemaname;
		String classification;
		int castno;
		ArrayList<String> cast;
		Type type = null;
		Cineplex cineplex;
		Cinema cinema;	
		


		Scanner sc = new Scanner(System.in);	


		List<String> useracc = Storage.getAdmins();

		String user, password;
		while (true) {
			System.out.println("Enter username");
			user = sc.next();
			System.out.println("Enter password");
			password = sc.next();
			if (useracc.contains(user) && useracc.contains(password)) {
				if (useracc.indexOf(user) == useracc.indexOf(password) - 1) {
					System.out.println("Login successful!");
					break;
				}
			} else {
				System.out.println("Incorrect username or password! Please try again");
			}
		}
		a =sc.nextLine();
		String choice;
    	/* Need to initialize here for Case 3 - Find movie in movie array */
		int j = 0;
		ArrayList<Movie> initialmovies = new ArrayList<Movie>();
		initialmovies = Storage.getMovieList();

		while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1 - Configure System Settings");
			System.out.println("2 - Enter New Movie");
			System.out.println("3 - Update Movie Details");
			System.out.println("4 - Remove Movie");
			System.out.println("5 - List Top 5 Ranking Movies");
			System.out.println("6 - Quit");
			choice = sc.nextLine();
			switch (choice) {
				case "1": {
					System.out.println("1 - Change Ticket Prices");
					System.out.println("2 - Add/Remove Holidays");
					if (sc.nextLine().equals("1")) {
                        Price price = Storage.getPrice();
						System.out.printf("Current prices:\nChild: $%.2f\nAdult: $%.2f\nSenior: $%.2f\n3D Movie add-on: $%.2f\nPlatinum Suite add-on: $%.2f\nWeekend/Holiday add-on: $%.2f\n", price.getChildPrice(), price.getAdultPrice(), price.getSeniorPrice(), price.getThreeD(), price.getPlatinum(), price.getWkendHol());

						String opt;

						System.out.println("");
						while (true) {
							System.out.println("Which price would you like to change?");
							System.out.println("1 - Child ticket price");
							System.out.println("2 - Adult ticket price");
							System.out.println("3 - Senior ticket price");
							System.out.println("4 - 3D Movie add-on");
							System.out.println("5 - Platinum Suite add-on");
							System.out.println("6 - Weekend/Holiday add-on");
							System.out.println("7 - Back");
							opt = sc.nextLine();
							if (opt.equals("1")) {
								System.out.println("Enter the new Child ticket price");
								price.setChildPrice( sc.nextDouble());
								System.out.printf("The new Child ticket price is now $%.2f\n",price.getChildPrice());
							} else if (opt.equals("2")) {
								System.out.println("Enter the new Adult ticket price");
                                price.setAdultPrice( sc.nextDouble());
								System.out.printf("The new Adult ticket price is now $%.2f\n",price.getAdultPrice());
							} else if (opt.equals("3")) {
								System.out.println("Enter the new Senior ticket price");
                                price.setSeniorPrice( sc.nextDouble());
								System.out.printf("The new Senior ticket price is now $%.2f\n",price.getSeniorPrice());
							} else if (opt.equals("4")) {
								System.out.println("Enter the new 3D Movie add-on price");
                                price.setThreeD( sc.nextDouble());
								System.out.printf("The new 3D Movie add-on price is now $%.2f\n",price.getThreeD());
							} else if (opt.equals("5")) {
								System.out.println("Enter the new Platinum Suite add-on price");
                                price.setPlatinum( sc.nextDouble());
								System.out.printf("The new PLatinum Suite add-on price is now $%.2f\n",price.getPlatinum());
							} else if (opt.equals("6")) {
								System.out.println("Enter the new Weekend/Holiday add-on price");
                                price.setWkendHol( sc.nextDouble());
								System.out.printf("The new Weekend/Holiday add-on price is now $%.2f\n",price.getWkendHol());
							} else if (opt.equals("7")) {
								break;
							} else {
								System.out.println("Invalid option! Try again!");
							}

							a = sc.nextLine();
							Storage.writePrice(price);
							
					
								
						}

					} else {
						ArrayList<String> hols = Storage.getHolidays();
						
						String opt;
						while (true) {
							System.out.println("What would you like to do?");
							System.out.println("1 - Remove Holiday");
							System.out.println("2 - Add Holiday");
							System.out.println("3 - Back to Main Menu");
							opt = sc.nextLine();
							if (opt.equals("1")) {//remove holiday
								String holtodelete;
								while (true) {
									System.out.println("Here are the current holidays:");
									for (int x = 0; x < hols.size(); x++) {
										System.out.println(hols.get(x));
									}
									System.out.println("Enter the holiday to delete in the format YYYY-MM-DD");
									holtodelete = sc.nextLine();
									if (!hols.contains(holtodelete)) {
										System.out.println("Holiday does not exist! Try again!");
									} else {
										break;
									}
								}
								hols.remove(holtodelete);
								System.out.println("Holiday " +  holtodelete + " successfully removed!");

							} else if (opt.equals("2")) {
								String newhol;
								while (true) {
									System.out.println("Here are the current holidays:");
									for (int x = 0; x < hols.size(); x++) {
										System.out.println(hols.get(x));
									}
									System.out.println("Enter the new holiday in the format YYYY-MM-DD");
									newhol = sc.nextLine();
									if (hols.contains(newhol)) {
										System.out.println("Holiday alreadys exists! Try again!");
									} else {
										if (isValidDate(newhol)) {
											break;
										} else {
											System.out.println("Not a valid date! Try again!");
										}
									}
								}
								hols.add(newhol);
								System.out.println(newhol + " added as a holiday!");
							} else if (opt.equals("3")) {
								break;
							} else {
								System.out.println("Invalid option!");
							}
						}
						Storage.writeHolidays(hols);
					}
					break;
				}    //Change ticket price
				case "2": {
					
    			/* Input movie title */
					System.out.println("Enter Movie Title: ");
					title = sc.nextLine();

    			/* Input director name and movie duration */
					System.out.println("Enter Director Name: ");
					director = sc.nextLine();
					System.out.println("Enter Movie Duration (in minutes): ");
					duration = sc.nextLine();
    			/* Input number of cast members */
					System.out.println("Enter the number of cast members ");
					castno = sc.nextInt();

    			/* dummy scanner */
					a = sc.nextLine();
    			/* Creating a array list of strings to store the casts' name */
					cast = new ArrayList<String>();
					System.out.println("Enter cast name");
					String string1 = new String();
					for (int y = 0; y < castno; y++) {
						string1 = sc.nextLine();
						cast.add(string1);
					}
    			/* Deciding the type of movie */
					System.out.println("Enter Movie Type");
					System.out.println("1 - 3D\n2 - Normal\n3 - Blockbuster");
					option = sc.nextLine();
					if (option.equals("1")) {
						type = Type.ThreeD;
					} else if (option.equals("2")) {
						type = Type.NORMAL;
					} else if (option.equals("3"))
						type = Type.BLOCKBUSTER;
				}
    			/* Input the start and end date of the movie */
				while(true){
					System.out.println("Enter the start date in the format YYYY-MM-DD");
					startDate = sc.nextLine();
					System.out.println("Enter the end date in the format YYYY-MM-DD");
					endDate = sc.nextLine();
				if(startDate.compareTo(endDate)<0 && isValidDate(startDate) && isValidDate(endDate)){
					break;
				}		
					if(!isValidDate(startDate)||!isValidDate(endDate)){
						System.out.println("Invalid date error!");
					}else{
						System.out.println("End date must be later than start date!");
					}
				}
					
				/* Input a short movie abstract */
				System.out.println("Enter a short movie abstract : ");
				movieabstract = sc.nextLine();
				/* Input the movie classification */
				System.out.println("Enter the movie classification (eg. PG/NC16/M18/R21) : ");
				classification = sc.nextLine();
				/* Instantiating the new movie object */
				Movie newmovie = new Movie(title, director, duration, cast, type, startDate, endDate, movieabstract,classification);
				/* Creating a new movie arraylist to retrieve and store from storage */
				ArrayList<Movie> movies = new ArrayList<Movie>();
				movies = Storage.getMovieList();
				/* Add the new movie to the movielist that we have and then add to storage*/
				movies.add(newmovie);
				Storage.writeMovieList(movies);
				System.out.println("New movie has been successfully added");
				/* Print all movies from list to show all movies including the one that is newly added */
				int l;
				for (l=0;l<movies.size();l++)
				{
					System.out.println(movies.get(l).toString());
					System.out.println();
				}
				System.out.println();
				break;
				

				case "3": {
					/* Retrieve and store movielist into movies1*/
					String updatemovie = new String();
					ArrayList<Movie> movies1 = new ArrayList<Movie>();
					movies1 = Storage.getMovieList();
					int n;
					/* Print all movies from list for user to choose*/
					for (n=0;n<movies1.size();n++)
					{
						System.out.println(movies1.get(n).toString());
						System.out.println();
					}
					
					boolean key = true;
					while (true) {
						while (key) {
							System.out.println(" Which movie would u like to update? Key in the movie's title");
							updatemovie = sc.nextLine();
    				/* to get the index of the object in the Movie's array */
							for (j = 0; j < movies1.size(); j++) {
								if (movies1.get(j).getTitle().equals(updatemovie)) {
									key = false;
									break;
								}

							}
							if (key == true)
								System.out.println("Movie entered is invalid! Please try again!");
						}
						/* Prompt the admin to choose what to update*/
						System.out.println("Movie found! What would u like to update for this movie? ");
						System.out.println(" 1 : Movie's Title  ");
						System.out.println(" 2 : Director's Name");
						System.out.println(" 3 : Movie's Duration");
						System.out.println(" 4 : Movie's Type");
						System.out.println(" 5 : Movie's start date");
						System.out.println(" 6 : Movie's end date");
						System.out.println(" 7 : Movie's cast");
						System.out.println(" 8 : Movie's abstract ");
						System.out.println(" 9 : Movielisting");
						System.out.println("10 : Movie's showing status");
						System.out.println("11 : Movie's classification");
						System.out.println("12 : Quit");

						String editchoice;

						editchoice = sc.nextLine();

						switch (editchoice) {
							/* Update the new title */
							case "1": {
								System.out.println("Please input the new movie title :  ");
								String newtitle;
								newtitle = sc.nextLine();
								movies1.get(j).setTitle(newtitle);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's title has been successfully updated ");
								break;
							}
							/*Update the new director */
							case "2": {
								System.out.println("Please input the new Director's :  ");
								String directname = new String();
								directname = sc.nextLine();
								movies1.get(j).setDirectorname(directname);
								Storage.writeMovieList(movies1);
								System.out.println("Director's name has been successfully updated ");
								break;
							}
							/*Update the new movie's duration*/
							case "3": {
								System.out.println("Please input the new Movie's duration : ");
								String newDuration = "0";
								newDuration = sc.next();
								a = sc.nextLine();
								movies1.get(j).setDuration(newDuration);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's duration has been successfully updated");
								break;
							}
							/*Update the new movie's type */
							case "4": {
								System.out.println("Please input the new Movie's Type : ");
								System.out.println("1 : TREED");
								System.out.println("2 : NORMAL");
								System.out.println("3 : BLOCKBUSTER");
								String typenumber = "0";
								typenumber = sc.nextLine();
								movies1.get(j).setType(typenumber);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's Type has been successfully changed");
								break;
							}
							/*Update the new movie start date */
							case "5": {
			
								while(true){
								System.out.println("Please input the Movie's new start date (in YYYY-MM-DD) : ");
								String newstartDate = new String();
								newstartDate = sc.nextLine();
								if(isValidDate(newstartDate) && newstartDate.compareTo(movies1.get(j).getEndDate())<0){
									movies1.get(j).setStartDate(newstartDate);
									Storage.writeMovieList(movies1);
									System.out.println("Movie's start date has been successfully updated");
									break;}
								
								if(!isValidDate(newstartDate)){
									System.out.println("Invalid date!");
								}else{
									System.out.println("Start date must be earlier than end date!");
								}
								
							}
								
								break;
							}
							/*Update the new movie end date*/
							/*If want to change both start and end date, need to change end date first due to compareto*/
							case "6": {
								
								while(true){
									System.out.println("Please input the Movie's new end date (in YYYY-MM-DD) : ");
									String newendDate = new String();
									newendDate = sc.nextLine();
									if(isValidDate(newendDate) && newendDate.compareTo(movies1.get(j).getStartDate())>0){
										movies1.get(j).setEndDate(newendDate);
										Storage.writeMovieList(movies1);
										System.out.println("Movie's end date has been successfully updated");
										break;}
									if(!isValidDate(newendDate)){
										System.out.println("Invalid date!");
									}else{
										System.out.println("End date must be later than start date!");
									}
								}
								break;
							}
							/*Update the new cast name from old cast name*/
							case "7": {
								System.out.println("Please enter the cast name that you would like to update");
								int k;
								System.out.println(movies1.get(j).getCast());
								String oldcastname = new String();
								oldcastname = sc.nextLine();
								/* j is already the index of the movie in the movie arraylist */
								/* Use k to iterate in the cast list inside movie */
								for (k = 0; k < movies1.get(j).getCast().size(); k++) {
									if (movies1.get(j).getCast().get(k).equals(oldcastname))
										break;
									
								}
								System.out.println("Please enter the new name that you would like to change it to ");
								String newcastname = new String();
								newcastname = sc.nextLine();
								movies1.get(j).setCast(newcastname, k);
								Storage.writeMovieList(movies1);
								System.out.println("Cast's name has been updated successfully");
								break;
							}
							/*Update the movie abstract*/
							case "8":{
								System.out.println(movies1.get(j).getMovieAbstract());
								System.out.println("Please enter the new movie abstract : ");
								String newmovieabstract;
								newmovieabstract = sc.nextLine();
								movies1.get(j).setMovieAbstract(newmovieabstract);
								Storage.writeMovieList(movies1);
								System.out.println("Movie abstract has been updated successfully");
								break;
							}
							/*Update - Create/Edit/Remove movielisting*/
							case "9":{
								int oldcineplex;
								int oldcinema;
								int updatecineplex;
								int updatecinema;
								String oldshowing;
								String updateshowing;
								String option1;
								int z,y;
								ArrayList<Cineplex> cineplexlist1 = new ArrayList<Cineplex>();
								cineplexlist1 = Storage.getCineplexList();
								movies1.get(j).showAdminMovieListings();
								System.out.println("What would u like to update?");
								System.out.println("1 : Create movielisting");
								System.out.println("2 : Edit movielisting");
								System.out.println("3 : Remove movielisting");
								System.out.println("4 : Quit");
								option1 = sc.nextLine();
								switch (option1) {
								/*Create new movielisting*/
								case "1" :
								{
									
									int newcineplex;
									int newcinema;
									String newshowing;
									while (true) {
									System.out.println("Choose the cineplex : ");
									System.out.println("1 : Cineplex 1 " );
									System.out.println("2 : Cineplex 2 ");
									System.out.println("3 : Cineplex 3 " );
									newcineplex = sc.nextInt();
									if (newcineplex == 1 || newcineplex == 2|| newcineplex==3)
										break;
									else 
										System.out.println("Invalid value entered.Please try again!");
									}
									
									/* dummy scanner */
									a= sc.nextLine();
									
									while(true) {
										System.out.println("Choose the cinema : ");
										System.out.println("1 : IMAX 1 " );
										System.out.println("2 : IMAX 2 ");
										System.out.println("3 : IMAX 3 " );
										newcinema = sc.nextInt();
										if (newcinema == 1 || newcinema == 2|| newcinema==3)
											break;
										else 
											System.out.println("Invalid value entered.Please try again!");
									}
									
									System.out.println("Please enter the showing time in this format : eg . YYYY-MM-DD | 20:00" );
									a = sc.nextLine();
									newshowing = sc.nextLine();
									ArrayList<Cineplex> cineplexlist = new ArrayList<Cineplex>();
									cineplexlist = Storage.getCineplexList(); 
									movies1.get(j).addMovieListing(cineplexlist.get(newcineplex-1), cineplexlist.get(newcineplex-1).getCinema(newcinema-1), newshowing);
									System.out.println("The following movielisting has been successfully added! " + cineplexlist.get(newcineplex-1).getName() + "|| " + cineplexlist.get(newcineplex-1).getCinema(newcinema-1).getName() + "|| " + newshowing );
									Storage.writeMovieList(movies1);
									break;
								}
								/*Change movielisting from existing listings*/
								case "2" :
								{
									System.out.println("For updating..." );
									while (true) {
										while(true) {
											System.out.println("Please choose the cineplex that the movie is currently in : ");
											System.out.println("1 : Cineplex 1 " );
											System.out.println("2 : Cineplex 2 ");
											System.out.println("3 : Cineplex 3 " );
											oldcineplex = sc.nextInt();
											if (oldcineplex == 1 || oldcineplex == 2|| oldcineplex==3)
												break;
											else 
												System.out.println("Invalid value entered.Please try again!");
										}	
										
										/* dummy scanner */
										a= sc.nextLine();
										
										while(true) {
											System.out.println("Please choose the cinema that the movie is currently in : ");
											System.out.println("1 : IMAX 1 " );
											System.out.println("2 : IMAX 2 ");
											System.out.println("3 : IMAX 3 " );
											oldcinema = sc.nextInt();
											if (oldcinema == 1 || oldcinema == 2|| oldcinema ==3)
												break;
											else 
											System.out.println("Invalid value entered.Please try again!");
										}	
										/* dummy scanner */
										a= sc.nextLine();
									
										System.out.println("Please enter the movie showing time in this format : eg . YYYY-MM-DD | 20:00" );
										oldshowing = sc.nextLine();
										
										int c = 0;
										/* search through all movielisting */
										for (z = 0; z< movies1.get(j).getAllMovieListing().size(); z++)
										{
											/* check for same cineplex name to enter inner for loop */
											if (movies1.get(j).getAdminMovieListing(z).getCineplexName().equals(cineplexlist1.get(oldcineplex-1).getName())) 
											{
												/* check which of the 3 cinemas */
											
													if (movies1.get(j).getAdminMovieListing(z).getCinemaName().equals(cineplexlist1.get(oldcineplex-1).getCinema(oldcinema-1).getName()))	
														/*Check for the same showing time */
														if (movies1.get(j).getAdminMovieListing(z).getShowing().equals(oldshowing)) {
															System.out.println("Movielisting found!");
															c = 1;
															break;
													
							
												}
											}
										}
										if (c != 1)
											System.out.println("Selected Movielisting do not exist! ");
										else if (c == 1){
											break;}
										
			
									}
									
									/*Enter new movielisting to change to after finding the old one*/
									while (true) {
										System.out.println("Please choose the new cineplex : ");
										System.out.println("1 : Cineplex 1 " );
										System.out.println("2 : Cineplex 2 ");
										System.out.println("3 : Cineplex 3 " );
										updatecineplex = sc.nextInt();
										if (updatecineplex == 1 || updatecineplex == 2|| updatecineplex==3)
											break;
										else 
											System.out.println("Invalid value entered.Please try again!");
									}
									while (true) {
										System.out.println("Please choose the new cinema : ");
										System.out.println("1 : IMAX 1 " );
										System.out.println("2 : IMAX 2 ");
										System.out.println("3 : IMAX 3 " );
										updatecinema = sc.nextInt();
										if (updatecinema == 1 || updatecinema == 2|| updatecinema ==3)
											break;
										else 
											System.out.println("Invalid value entered.Please try again!");
										}
									
									System.out.println("Please enter the showing time in this format : eg . YYYY-MM-DD | 20:00" );
									a = sc.nextLine();
									updateshowing = sc.nextLine();
									movies1.get(j).getAdminMovieListing(z).setCineplex(cineplexlist1.get(updatecineplex-1).getName());
									movies1.get(j).getAdminMovieListing(z).setCinema(cineplexlist1.get(updatecineplex-1).getCinema(updatecinema-1).getName());
									movies1.get(j).getAdminMovieListing(z).setShowing(updateshowing);
									System.out.println("Movielisting has been successfully updated to ");
									System.out.println(cineplexlist1.get(updatecineplex-1).getName() + "|| " + cineplexlist1.get(updatecineplex-1).getCinema(updatecinema-1).getName() + "|| " + updateshowing);
									Storage.writeMovieList(movies1);
									break;
										
								}
								/*Remove existing movielisting*/
								case "3" :
								{
								int delcineplex;
								int delcinema;
								String delshowing;
								movies1.get(j).showAdminMovieListings();
								System.out.println("Select the movie listing that you would like to delete");
								while (true) {
									while(true) {
										System.out.println("Please choose the cineplex that the movie is currently in : ");
										System.out.println("1 : Cineplex 1 " );
										System.out.println("2 : Cineplex 2 ");
										System.out.println("3 : Cineplex 3 " );
										delcineplex = sc.nextInt();
										if (delcineplex == 1 || delcineplex == 2|| delcineplex==3)
											break;
										else 
											System.out.println("Invalid value entered.Please try again!");
									}	
									
									/* dummy scanner */
									a= sc.nextLine();
									
									while(true) {
										System.out.println("Please choose the cinema that the movie is currently in : ");
										System.out.println("1 : IMAX 1 " );
										System.out.println("2 : IMAX 2 ");
										System.out.println("3 : IMAX 3 " );
										delcinema = sc.nextInt();
										if (delcinema == 1 || delcinema == 2|| delcinema ==3)
											break;
										else 
										System.out.println("Invalid value entered.Please try again!");
									}	
									/* dummy scanner */
									a= sc.nextLine();
								
									System.out.println("Please enter the movie showing time in this format : eg . YYYY-MM-DD | 20:00" );
									delshowing = sc.nextLine();
									
									int c = 0;
									int t = 0;
									/* search through all movie listing */
									for (t = 0; t< movies1.get(j).getAllMovieListing().size(); t++)
									{
										/* check for same cineplex name to enter inner for loop */
										if (movies1.get(j).getAdminMovieListing(t).getCineplexName().equals(cineplexlist1.get(delcineplex-1).getName())) 
										{
											/* check which of the 3 cinemas */
												if (movies1.get(j).getAdminMovieListing(t).getCinemaName().equals(cineplexlist1.get(delcineplex-1).getCinema(delcinema-1).getName()))	
													/*check for the same showing time then finally delete and write back to update it in binary*/
													if (movies1.get(j).getAdminMovieListing(t).getShowing().equals(delshowing)) {
														System.out.println("Movielisting found and deleted!");
														movies1.get(j).getAllMovieListing().remove(t);
														Storage.writeMovieList(movies1);
														
														c = 1;
														break;
												
						
											}
										}
									}
									if (c != 1)
										System.out.println("Selected Movielisting do not exist! ");
									else if (c == 1)
									{
										break;
									}
								}
								break;
								}
								case "4" : break;
								}
								break;
							}
							/*Update movie status*/
							case "10":{
								String opt;
								while(true){
									
									System.out.println("What do you want to change the showing status to?");
									System.out.println("1 - Coming soon");
									System.out.println("2 - Preview");
									System.out.println("3 - Now showing");
									System.out.println("4 - End of showing");
									opt = sc.nextLine();
									if(opt.equals("1")||opt.equals("2")||opt.equals("3")||opt.equals("4")){
										
										break;
									}
										 
								}
								
								movies1.get(j).setStatus(opt);
								System.out.println("Movie's status has been successfully updated! ");
								Storage.writeMovieList(movies1);
								break;
								
							}
							/*Update movie classification*/
							case "11":{
								
								System.out.println("Enter the movie classification (eg. PG/NC16/M18/R21) : ");
								String c = sc.nextLine();
								movies1.get(j).setMovieClass(c);
								System.out.println("Movie's classification has been successfully updated! ");
								Storage.writeMovieList(movies1);
								break;
							}
							/*To terminate*/
							case "12":{
								break;
							}	
							
						}
						break;
					}
					break;
				}
				/*Remove movie*/
				case "4": {
					ArrayList<Movie> movies2 = new ArrayList<Movie>();
					movies2 = Storage.getMovieList();
					System.out.println("Which movie would u like to remove?");
					int m;
					for (m=0;m<movies2.size();m++)
					{
						System.out.println(movies2.get(m).getTitle());
						System.out.println();
					}					
					System.out.println("Please enter the movie that you would like to remove : ");
					String delmovie = new String();
					delmovie = sc.nextLine();
					int q=0;
					boolean key1 = false;
	    			/* to get the index of the object in the Movie's array */
					while (!key1) 
					{
						for (q = 0; q < movies2.size(); q++) {
							if (movies2.get(q).getTitle().equals(delmovie)) {
								key1 = true;
								break;
							}
						}
						if (key1 == false){
							System.out.println("Invalid movie entered, Please try again! ");
							System.out.println("Please enter the movie that you would like to remove : ");
							delmovie = sc.nextLine();
						}
					}
					movies2.remove(q);
					Storage.writeMovieList(movies2);
					System.out.println( delmovie +" has been successfully removed");
					break;
				}
		
				/*List top 5 movie by sales or rating*/
				case "5": {
					//order by sales or rating
	    			System.out.println("1 : By Sales ");
	    			System.out.println("2 : By Rating ");
	    			System.out.println("3 : Quit");
	    			String option1;
	    			ArrayList<Movie> movies3 = new ArrayList<Movie>(); 
	    			movies3 = Storage.getMovieList();
	    			option1 = sc.nextLine();
	    			switch(option1) {
	    			case "1" :{ 
	    			System.out.println(UserApp.top5Movies(movies3,UserApp.tickets));
	    			break;
	    			}
	    			case "2" : {
	    			System.out.println(UserApp.top5Movies(movies3, UserApp.ratings));
	    			break;
	    			}
	    			case "3" :
	    			break;
	    			}
				}
				case "6": {
					System.out.println("Bye!");
					break;

				} //quit
			}

			if (choice.equals("6")) {
				break;            //Quit program
			}
		}
	}
}


			
		
	

	




	
  
    
    	
    

