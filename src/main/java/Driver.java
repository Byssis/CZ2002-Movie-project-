 /* Created by Albin on 2017-10-12.
*/
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.text.*;

public class Driver {

	public static boolean isValidDate(String inDate) {                        //check if the date passed in is a valid one
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


		//ArrayList<String> hols = Storage.getHolidays();						//read in public holidays

		double childprice = 0;
		double adultprice = 0;
		double seniorprice = 0;
		double threeD = 0;
		double platinum = 0;
		double wkendhol = 0;
		UserApp userapp;
		String title;
		String director;
		String duration;
		String movieabstract;
		String option;
		String startDate;
		String endDate;
		String a; //dummy scanner
		String cineplexname;
		String cinemaname;
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

						try {
							File price = new File("/Users/Public/prices.txt");                        //read in ticket prices
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
						} catch (Exception e) {
							System.out.println("File not Found!");
						}

						System.out.printf("Current prices:\nChild: $%.2f\nAdult: $%.2f\nSenior: $%.2f\n3D Movie add-on: $%.2f\nPlatinum Suite add-on: $%.2f\nWeekend/Holiday add-on: $%.2f\n", childprice, adultprice, seniorprice, threeD, platinum, wkendhol);

						String opt;

						System.out.println("");
						while (true) {
							System.out.println("Which price would you like to change?");
							System.out.println("1 - Child ticket price");
							System.out.println("2 - Adult ticket price");
							System.out.println("3 - Senior ticket price");
							System.out.println("4 - 3D Movie add-on");
							System.out.println("5 - Platinum Suit add-on");
							System.out.println("6 - Weekend/Holiday add-on");
							System.out.println("7 - Back");
							opt = sc.nextLine();
							if (opt.equals("1")) {
								System.out.println("Enter the new Child ticket price");
								childprice = sc.nextDouble();
							} else if (opt.equals("2")) {
								System.out.println("Enter the new Adult ticket price");
								adultprice = sc.nextDouble();
							} else if (opt.equals("3")) {
								System.out.println("Enter the new Senior ticket price");
								seniorprice = sc.nextDouble();
							} else if (opt.equals("4")) {
								System.out.println("Enter the new 3D Movie add-on price");
								threeD = sc.nextDouble();
							} else if (opt.equals("5")) {
								System.out.println("Enter the new Platinum Suite add-on price");
								platinum = sc.nextDouble();
							} else if (opt.equals("6")) {
								System.out.println("Enter the new Weekend/Holiday add-on price");
								wkendhol = sc.nextDouble();
							} else if (opt.equals("7")) {
								break;
							} else {
								System.out.println("Invalid option! Try again!");
							}
							try {
								PrintWriter newprice = new PrintWriter("/Users/Public/prices.txt");
								newprice.println(childprice);
								newprice.println(adultprice);
								newprice.println(seniorprice);
								newprice.println(threeD);
								newprice.println(platinum);
								newprice.println(wkendhol);
								newprice.close();
							} catch (Exception e) {
								System.out.println("Error!");
							}

						}

					} else {
						ArrayList<String> hols = Storage.getHolidays();
						System.out.println("Here are the current holidays:");
						for (int x = 0; x < hols.size(); x++) {
							System.out.println(hols.get(x));
						}
						String opt;
						while (true) {
							System.out.println("What would you like to do?");
							System.out.println("1 - Remove Holiday");
							System.out.println("2 - Add Holiday");
							System.out.println("3 - Back to Main Menu");
							opt = sc.nextLine();
							if (opt.equals("1")) {//remove holiday
								a = sc.nextLine();
								String holtodelete;
								while (true) {
									System.out.println("Enter the new holiday in the format Day of Week, Month Day. eg. Monday, May 1");
									holtodelete = sc.nextLine();
									if (!hols.contains(holtodelete)) {
										System.out.println("Holiday does not exist! Try again!");
									} else {
										break;
									}
								}
								hols.remove(holtodelete);
								System.out.println("Holiday successfully removed!");

							} else if (opt.equals("2")) {
								String newhol;
								while (true) {
									System.out.println("Enter the new holiday in the format Day of Week, Month Day. eg. Monday, May 1");
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
				System.out.println("Enter the start date in the format YYYY MM DD");
				startDate = sc.nextLine();
				System.out.println("Enter the end date in the format YYYY MM DD");
				endDate = sc.nextLine();
				
				System.out.println("Enter a short movie abstract : ");
				movieabstract = sc.nextLine();
				
				/* Instantiating the movie object */
				Movie newmovie = new Movie(title, director, duration, cast, type, startDate, endDate, movieabstract);
				
				while(true) {
				System.out.println("Enter the number of movielistings : ");
				int noofMovieListing = sc.nextInt();
				
				/* dummy scanner */
				a= sc.nextLine();
				
				String newcineplex;
				String newcinema;
				
				System.out.println("Choose the cineplex : ");
				System.out.println("1 : Cineplex 1 " );
				System.out.println("2 : Cineplex 2 ");
				System.out.println("3 : Cineplex 3 " );
					
				newcineplex = sc.nextLine();
					
				if (newcineplex.equals("1")) 
				{
					System.out.println("Choose the cinema :  ");
					System.out.println("1 : IMAX 1 ");
					System.out.println("2 : IMAX 2  ");
					System.out.println("3 : IMAX 3 " );
				}
				else if (newcineplex.equals("2")) 
				{
					System.out.println("Choose the cinema :  ");
					System.out.println("4 : IMAX 4 ");
					System.out.println("5 : IMAX 5  ");
					System.out.println("6 : IMAX 6 " );
				}
				else if (newcineplex.equals("3")) 
				{
					System.out.println("Choose the cinema :  ");
					System.out.println("7 : IMAX 7 ");
					System.out.println("8 : IMAX 8  ");
					System.out.println("9 : IMAX 9 " );
				}
				newcinema = sc.nextLine();
				System.out.println("Please enter the showing time in this format : eg . YYYY-DD-MM | 20:00" );
				
				
    			
				ArrayList<Movie> movies = new ArrayList<Movie>();
				//MovieListing newmovielisting = new MovieListing(newmovie,);
				movies = Storage.getMovieList();
				movies.add(newmovie);
    			/* Adding it into movie's dataset */
				Storage.writeMovieList(movies);
				System.out.println(movies);
				System.out.println();
				break;
				}

				case "3": {
					String updatemovie = new String();
					ArrayList<Movie> movies1 = new ArrayList<Movie>();
					movies1 = Storage.getMovieList();
					System.out.println(movies1);
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

						System.out.println("Movie found! What would u like to update for this movie? ");
						System.out.println(" 1 : Movie's Title  ");
						System.out.println(" 2 : Director's Name");
						System.out.println(" 3 : Movie's Duration");
						System.out.println(" 4 : Movie's Type");
						System.out.println(" 5 : Movie's start date");
						System.out.println(" 6 : Movie's end date");
						System.out.println(" 7 : Movie's casts");
						System.out.println(" 8 : Movie's abstract ");
						System.out.println(" 9 : Movielisting");
						System.out.println("10 : Quit");

						String editchoice;

						editchoice = sc.nextLine();

						switch (editchoice) {
							case "1": {
								System.out.println("Please input the new movie title :  ");
								String newtitle;
								newtitle = sc.nextLine();
								movies1.get(j).setTitle(newtitle);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's title has been successfully updated ");
								break;
							}
							case "2": {
								System.out.println("Please input the new Director's :  ");
								String directname = new String();
								directname = sc.nextLine();
								movies1.get(j).setDirectorname(directname);
								Storage.writeMovieList(movies1);
								System.out.println("Director's name has been successfully updated ");
								break;
							}
							case "3": {
								System.out.println("Please input the new Movie's duration : ");
								String newDuration = "0";
								newDuration = sc.next();
								movies1.get(j).setDuration(newDuration);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's duration has been successfully updated");
								break;
							}
							case "4": {
								System.out.println("Please input the new Movie's Type : ");
								System.out.println("1 : TREED");
								System.out.println("2 : NORMAL");
								System.out.println("3 : BLOCKBUSTER");
								String typenumber = "0";
								typenumber = sc.next();
								movies1.get(j).setType(typenumber);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's Type has been successfully changed");
								break;
							}
							case "5": {
								System.out.println("Please input the Movie's new start date : ");
								String newstartDate = new String();
								newstartDate = sc.nextLine();
								movies1.get(j).setStartDate(newstartDate);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's start date has been successfully updated");
								break;
							}
							case "6": {
								System.out.println("Please input the Movie's new end date : ");
								String newendDate = new String();
								newendDate = sc.nextLine();
								movies1.get(j).setEndDate(newendDate);
								Storage.writeMovieList(movies1);
								System.out.println("Movie's end date has been successfully updated");
								break;
							}
							case "7": {
								System.out.println("Please enter the cast name that you would like to update");
								int k;
								String oldcastname = new String();
								oldcastname = sc.nextLine();
    				/* j is already the index of the movie in the movie arraylist */
    				/* Use k to iterate in the cast list inside movie */
								for (k = 1; k < movies1.get(j).getCast().size(); k++) {
									if (movies1.get(j).getCast().get(k).equals(oldcastname))
										break;
									System.out.println(movies1.get(j).getCast().size());
								}
								System.out.println("Please enter the new name that you would like to change it to ");
								String newcastname = new String();
								newcastname = sc.nextLine();
								movies1.get(j).setCast(newcastname, k);
								Storage.writeMovieList(movies1);
								System.out.println("Cast's name has been updated successfully");
								break;
							}
							case "8":{
								System.out.println("Please enter the new movie abstract : ");
								String newmovieabstract;
								newmovieabstract = sc.nextLine();
								movies1.get(j).setMovieAbstract(newmovieabstract);
								Storage.writeMovieList(movies1);
								System.out.println("Movie abstract has been updated successfully");
								break;
							}
							case "9":{
								System.out.println("Please choose the ");
								
							}
							case "10":{
								break;
							}
							
								
							
						}
						break;
					}
					break;
				}
				case "4": {
    			/* dummy scanner */
					a = sc.nextLine();
					ArrayList<Movie> movies2 = new ArrayList<Movie>();
					movies2 = Storage.getMovieList();
					System.out.println(movies2);
					System.out.println("Please enter the movie that you would like to remove : ");
					String delmovie = new String();
					delmovie = sc.nextLine();
					int q = 0;
					boolean key1 = true;
    			/* to get the index of the object in the Movie's array */
					while (key1) {
						for (q = 0; q < movies2.size(); q++) {
							if (movies2.get(q).getTitle().equals(delmovie)) {
								key1 = false;
								break;
							}
						}
						if (key1 == true)
							System.out.println("Invalid movie entered, Please try again! ");
					}
					movies2.remove(q);
					Storage.writeMovieList(movies2);
					System.out.println("Movie has been successfully removed");
					break;
				}
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

				} //quit
			}

			if (choice.equals("6")) {
				break;            //Quit program
			}
		}

	}

}



	
  
    
    	
    

