import java.util.*;

/**
 * Created by Albin on 2017-10-20.
 */
public class UserApp {
     static List<Movie> movies = new ArrayList<Movie>();
     static List<MovieGoer> moviegoers = new ArrayList<MovieGoer>();

    public static void main(String [] args){
        String [] cast = {"Albin", "Robert", "Lars"};
        movies.add(new Movie("Die Hard", "Some director", 90, cast, Type.BLOCKBUSTER, new Date(), new Date()));
        movies.add(new Movie("Finding Nemo", "Some director", 90, cast, Type.BLOCKBUSTER, new Date(), new Date()));
        movies.add(new Movie("Planet of the apes", "Some director", 90, cast, Type.BLOCKBUSTER, new Date(), new Date()));
        movies.add(new Movie("Spiderman", "Some director", 90, cast, Type.BLOCKBUSTER, new Date(), new Date()));
        movies.add(new Movie("Superman", "Some director", 90, cast, Type.BLOCKBUSTER, new Date(), new Date()));
        MovieListing ml = new MovieListing(movies.get(1), new Cineplex("Kista SF"), new Cinema("Imax 1", CinemaType.NORMAL, 10, 10), new Date() );
        movies.get(1).addMovieListing(new Cineplex("Kista SF"), new Cinema("Imax 1", CinemaType.NORMAL, 10, 10), new Date());
        movies.get(1).addMovieListing(new Cineplex("Kista SF"), new Cinema("Imax 2", CinemaType.NORMAL, 10, 10), new Date());
        movies.get(1).addMovieListing(new Cineplex("Kista SF"), new Cinema("Imax 3", CinemaType.NORMAL, 10, 10), new Date());
        movies.get(1).addMovieListing(new Cineplex("Kista SF"), new Cinema("Imax 4", CinemaType.NORMAL, 10, 10), new Date());

        int option;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("User Menu: ");
            UIFunctions.divider();
            System.out.println("1: List all movies");
            System.out.println("2. Search movie");
            System.out.println("3. List top 5 movies");
            System.out.println("4. Booking history");
            System.out.println("5: Quit");
            UIFunctions.divider();
            System.out.print("Enter option: ");
            option = sc.nextInt();
            UIFunctions.clear();
            switch (option){
                case 1:         // List movies
                    listMovies(movies,true);
                    break;

                case 2:
                    searchMovies();
                    break;

                case 3:
                    listTop5Movies();
                    break;
                case 4:
                    showBookingHistory();
                    break;
                case 5:

                    break;

                default:            // Error

                    break;
            }

            UIFunctions.clear();
        } while (option < 5);
    }

    private static boolean listMovies(List<Movie> movies, boolean rating) {
        while (true) {
            UIFunctions.divider();
            Scanner sc = new Scanner(System.in);
            int i = 1;
            System.out.println("Title, " + ((rating)? "Rating" : "Ticket Sales"));
            for (Movie m : movies)
                System.out.println((i++) + ": " + m.getTitle() + ", " + ((rating)? m.averageRating() : m.getTicketSales()));
            System.out.println(i + ": Main menu: ");
            UIFunctions.divider();
            System.out.print("Enter option: ");
            int c = sc.nextInt();
            if (c > 0 && c < movies.size()) {
                //System.out.println(movies.get(c));
                // Booking menu
                if(movieMenu(movies.get(c - 1)))
                    return true;
            } else if(c == i) {
                // Go back
                System.out.println("Going back to main menu");
                return false;
            }
        }
    }

    private static void searchMovies(){
        /*
        ask user for search terms
        iterate through the array
        present the matched results
        user should be able to pick the options 
        go back to menu
        */
        UIFunctions.divider();
        System.out.print("Enter search term: ");
        
        Scanner sc = new Scanner(System.in);
        String searchterm = sc.next();
        
        List<Movie> searchresults = new ArrayList<Movie>();

        for (Movie m : movies)
            if(m.getTitle().toLowerCase().contains(searchterm.toLowerCase()))
                searchresults.add(m);

        if (searchresults.size() == 0)
            {
                System.out.println("Your search didn't return any results.");
                UIFunctions.waitForUser();
            }
        else{
            System.out.println("Results:");
            listMovies(searchresults,true);
        }
            
        } 

    private static boolean movieMenu(Movie movie) {
        /*
        Display movie info
        Ask user to show listing
            Show listing
        Review/Rating
        Go back
         */

        Scanner sc = new Scanner(System.in);
        int c = 0;
        do{
            UIFunctions.divider();
            System.out.println(movie);
            UIFunctions.divider();
            System.out.println("1. Show movie listings");
            System.out.println("2. Show reviews");
            System.out.println("3. Review Movie");
            System.out.println("4. Rate Movie");
            System.out.println("5. Go back");
            System.out.println("6. Main Menu");
            UIFunctions.divider();
            c = sc.nextInt();
            UIFunctions.clear();
            switch (c){
                case 1:
                    if (showListing(movie))
                        break;
                    break;
                case 2:
                    if(showReviews(movie))
                        break;
                    break;

                case 3:
                    if (reviewMovie(movie))
                        break;
                    break;

                case 4:
                    if (rateMovie(movie))
                        break;
                    break;
                case 5:
                    return false;
                default:            // Error
                    return true;
            }
        }while (c > 4);
        return false;
    }

    private static boolean rateMovie(Movie movie) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username(Press enter to finish):");
        String name = sc.nextLine();
        System.out.println("Enter rating(1-5):");
        int rating = sc.nextInt();
        if(rating < 1 || rating > 5){
            System.out.println("Invalid rating!");
            return false;
        }
        Rating r = new Rating(name, rating);

        movie.addRating(r);

        return false;
    }

    private static boolean reviewMovie(Movie movie) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username(Press enter to finish):");
        String name = sc.nextLine();

        System.out.println("Enter review(Press enter to finish): ");
        String review = sc.nextLine();

        Review r = new Review(name, review);

        movie.addReview(r);

        return false;
    }

    private static boolean showReviews(Movie movie) {
        movie.showReviews();
        UIFunctions.waitForUser();
        return false;
    }

    private static boolean showListing(Movie movie) {
        movie.showMovieListings();
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose movie listing: ");
        int c = sc.nextInt();
        if(c > 0) // && c > #movieListings
           return booking(movie,movie.getMovieListing(c));
        return false;
    }

    private static boolean booking(Movie movie,MovieListing movieListing) {
        /*
        Show movie
        Show movie listing
        Show available seats
        1. give the user option to pick seat
        or
        2. go back or go to main menu
         */

        while (true) {
            System.out.println(movie);
            System.out.println(movieListing);
            movieListing.displayCinema();

            Scanner sc = new Scanner(System.in);
            UIFunctions.divider();
            System.out.println("1. Choose seats");
            System.out.println("2. Back");
            System.out.println("3. Go to menu");
            System.out.print("Enter option: ");

            int c = sc.nextInt();
            if (c == 1) {
                List<Character> rows;
                List<Integer> setNrs;
                while (true) {
                    rows = new LinkedList<Character>();
                    setNrs = new LinkedList<Integer>();
                    System.out.print("Enter number of seats to book: ");
                    int nr = sc.nextInt();
                    for (int i = 0; i < nr; i++) {
                        System.out.println("Seat " + (i + 1));

                        System.out.print("Enter row:");
                        char row = sc.next().toCharArray()[0];

                        System.out.print("Enter seat nr :");
                        int seatNr = sc.nextInt();
                        if (movieListing.seatAvailable(row, seatNr)) {
                            rows.add((row));
                            setNrs.add(seatNr);
                            System.out.println("Seat: " + row + seatNr);
                        } else {
                            System.out.println("Seat: " + row + seatNr + " not available or not valid");
                        }
                    }
                    System.out.println("Seats correct?Y/n");
                    if (sc.next().toLowerCase().equals("y"))
                        break;
                }
                if (rows.size() > 0) {
                    payment(rows, setNrs, movieListing);
                    return true;
                }

            } else if (c == 2) {
                return false;
            } else if (c == 3) {
                return true;
            } else {
                System.out.println("Invalid option!");
                return false;
            }
        }
    }


    private static void payment(List<Character> rows, List<Integer> setNrs, MovieListing movieListing) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.next();
        System.out.print("Enter your phone: ");
        String phoneNumber = sc.next();

        MovieGoer user = findMovieGoer(name, phoneNumber);
        Seat[] seats = new Seat[rows.size()];
        for(int i = 0; i < rows.size(); i++){
            seats[i] = movieListing.bookSeats(rows.get(i), setNrs.get(i));
        }
        Booking booking = new Booking(user, movieListing, seats);

        user.addBooking(booking);

        System.out.println("Payments done! Press enter to continue...");
        UIFunctions.waitForUser();
    }

    private static MovieGoer findMovieGoer(String name, String phoneNumber) {
        
        for(MovieGoer mg : moviegoers){
            if (name.equals(mg.getName()) && (phoneNumber.equals(mg.getPhone()))){
                return mg;
            }
        }
        
        MovieGoer m = new MovieGoer(name, phoneNumber);
        moviegoers.add(m);

        return m;
    }

    private static void listTop5Movies(){
        UIFunctions.divider();
        System.out.println("Show top 5 based on: ");
        System.out.println("1. Overall reviewers' rating");
        System.out.println("2. Ticket Sales: ");
        System.out.println("3. Main menu: ");

        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();

        if (c == 1){
            listTop5MoviesRating();
        } else if (c == 2) {
            listTop5TicketSales();
        } else {
            return;
        }
    }

    private static void listTop5MoviesRating(){
        // iterate through movies list and save the top 5 movies into a list 

        List<Movie> top5moviesratings = new ArrayList<Movie>();

        for(int i = 0; i < 5; i++){
            Movie m = movies.get(0);
            
            for (Movie candidatemovie : movies){
                
                boolean inTop5 = false;

                if (candidatemovie.averageRating() >= m.averageRating()){
                    for (Movie top5movie : top5moviesratings){
                        if (candidatemovie.equals(top5movie))
                            inTop5 = true;
                    }
                    if (inTop5 == false)
                        m = candidatemovie;
                }
                    
            }
            top5moviesratings.add(m);
        }

        listMovies(top5moviesratings,true);

    }

    private static void listTop5TicketSales(){
        // iterate through movies list and save the top 5 movies into a list 

        List<Movie> top5moviesticketsales = new ArrayList<Movie>();

        for(int i = 0; i < 5; i++){
            Movie m = movies.get(0);
            
            for (Movie candidatemovie : movies){
                
                boolean inTop5 = false;

                if (candidatemovie.getTicketSales() >= m.getTicketSales()){
                    for (Movie top5movie : top5moviesticketsales){
                        if (candidatemovie.equals(top5movie))
                            inTop5 = true;
                    }
                    if (inTop5 == false)
                        m = candidatemovie;
                }
                    
            }
            top5moviesticketsales.add(m);
        }

        listMovies(top5moviesticketsales,false);

    }

    private static void showBookingHistory(){
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        
        System.out.println("Enter your phone number: ");
        String phone = sc.next();

        for(MovieGoer mg : moviegoers){
            if (name.equals(mg.getName()) && phone.equals(mg.getPhone())){
                mg.showBookingHistory();
                UIFunctions.waitForUser();
                return;
            }
        }
        System.out.println("No booking history for " + name);
        UIFunctions.waitForUser();
    }

}
