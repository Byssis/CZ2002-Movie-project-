import java.io.IOException;
import java.util.*;

/**
 * Created by Albin on 2017-10-20.
 */
public class UserApp {

    public static void main(String[] args) {
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            UIFunctions.clear();
            UIFunctions.divider();
            System.out.println("  _________                                \n" +
                    " /   _____/__ ________   ___________       \n" +
                    " \\_____  \\|  |  \\____ \\_/ __ \\_  __ \\      \n" +
                    " /        \\  |  /  |_> >  ___/|  | \\/      \n" +
                    "/_______  /____/|   __/ \\___  >__|         \n" +
                    "        \\/      |__|        \\/             \n" +
                    "_________ .__                              \n" +
                    "\\_   ___ \\|__| ____   ____   _____ _____   \n" +
                    "/    \\  \\/|  |/    \\_/ __ \\ /     \\\\__  \\  \n" +
                    "\\     \\___|  |   |  \\  ___/|  Y Y  \\/ __ \\_\n" +
                    " \\______  /__|___|  /\\___  >__|_|  (____  /\n" +
                    "        \\/        \\/     \\/      \\/     \\/ ");
            UIFunctions.divider();
            System.out.println("1: Book/Show movies");
            System.out.println("2. Search movie");
            System.out.println("3. List top 5 movies");
            System.out.println("4. Booking history");
            System.out.println("5: Quit");
            UIFunctions.divider();
            System.out.print("Enter option: ");
            option = sc.nextInt();
            UIFunctions.clear();
            switch (option) {
                case 1:         // List movies
                    List<Movie> movies = Storage.getMovieList();
                    listMovies(movies, true);
                    Storage.writeMovieList(movies);
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

    /**
     * List out all the movies
     *
     * @param movies
     * @param rating
     * @return true sends user back to mainmenu, false sends user up to the previous menu (which is the main menu too)
     * @see Movie
     * @see UIFunctions
     */
    private static boolean listMovies(List<Movie> movies, boolean rating) {
        while (true) {
            UIFunctions.divider();
            Scanner sc = new Scanner(System.in);
            int i = 1;
            System.out.println("Title, " + " Movie Status, " +((rating) ? "Rating" : "Ticket Sales"));
            UIFunctions.divider();
            for (Movie m : movies) {
                System.out.println((i++) + ": " + m.getTitle() + ", " + m.getStatusString() +  ", " + ((rating) ? m.averageRatingStr() : m.getTicketSales()));
            }
            System.out.println(i + ": Main menu: ");
            UIFunctions.divider();
            System.out.print("Enter option: ");
            int c = sc.nextInt();
            UIFunctions.clear();
            if (c > 0 && c < movies.size()+1) {
                //System.out.println(movies.get(c));
                // Booking menu
                Movie movie = movies.get(c - 1);
                if((movie.getStatus() == MovieStatus.COMING_SOON) ? movieMenuComingSoon(movie) : movieMenu(movie))
                    return true;
            } else if (c == i) {
                // Go back
                System.out.println("Going back to main menu");
                return false;
            }
        }
    }

    /**
     * Checks whether search term exists in the Movies.dat file
     * If there are any, they will be listed down and user can directly proceed to select movie listing
     *
     * @see Movie
     * @see Storage
     * @see UIFunctions
     */
    private static void searchMovies() {

        List<Movie> movies = Storage.getMovieList();
        UIFunctions.divider();
        System.out.print("Enter search term: ");

        Scanner sc = new Scanner(System.in);
        String searchterm = sc.next();

        List<Movie> searchresults = new ArrayList<Movie>();

        for (Movie m : movies)
            if (m.getTitle().toLowerCase().contains(searchterm.toLowerCase()))
                searchresults.add(m);

        if (searchresults.size() == 0) {
            System.out.println("Your search didn't return any results.");
            UIFunctions.waitForUser();
        } else {
            System.out.println("Results:");
            listMovies(searchresults, true);
        }

        Storage.writeMovieList(movies);

    }

    /**
     * Show coming soon movie
     * @param movie
     * @return true sends user back to mainmenu, false sends user up to the previous menu
     * @see Movie
     */
    private static boolean movieMenuComingSoon(Movie movie) {
        Scanner sc = new Scanner(System.in);
        UIFunctions.divider();
        System.out.println(movie);
        UIFunctions.divider();

        System.out.println("1. Go back");
        System.out.println("2. Main Menu");
        UIFunctions.divider();
        int c = sc.nextInt();
        return (c == 2) ? true : false;
    }

    /**
     * Shows menu of possible options and prompt user to choose, calls the chosen function
     *
     * @param movie
     * @return true sends user back to mainmenu, false sends user up to the previous menu
     * @see Movie
     */
    private static boolean movieMenu(Movie movie) {
        Scanner sc = new Scanner(System.in);
        int c = 0;
        do {
            UIFunctions.divider();
           // System.out.println("Movie menu");
            //UIFunctions.divider();
            System.out.println(movie);
            UIFunctions.divider();
            int i = 1;
            if (movie.getStatus() != MovieStatus.END_OF_SHOWING)
                System.out.println((i++) + ". Book movie");
            System.out.println((i++) + ". Show reviews");
            System.out.println((i++) + ". Review Movie");
            System.out.println((i++) + ". Rate Movie");
            System.out.println((i++) + ". Go back");
            System.out.println((i++) + ". Main Menu");
            UIFunctions.divider();
            c = sc.nextInt() + ((movie.getStatus() == MovieStatus.END_OF_SHOWING) ? 1 : 0);
            UIFunctions.clear();
            switch (c) {
                case 1:
                    if (showListing(movie))
                        break;
                    break;
                case 2:
                    if (showReviews(movie))
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
            UIFunctions.clear();
        } while (c < 5  );
        return false;
    }

    /**
     * Prompts user to enter name and rating given for movie, calls addRating() in Movie class to add
     *
     * @param movie
     * @return false sends user up to the previous menu once rating added
     * @see Movie
     */
    private static boolean rateMovie(Movie movie) {
        UIFunctions.divider();
        System.out.println(movie);
        UIFunctions.divider();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username(Press enter to finish):");
        String name = sc.nextLine();
        System.out.println("Enter rating(1-5):");
        int rating = sc.nextInt();
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating!");
            return false;
        }
        Rating r = new Rating(name, rating);

        movie.addRating(r);
        UIFunctions.clear();
        return false;
    }

    /**
     * Prompts user to enter name and review for movie, calls addReview() in movie class to add it in
     *
     * @param movie
     * @return sends user up to the previous menu once review added
     * @see Movie
     */
    private static boolean reviewMovie(Movie movie) {
        UIFunctions.divider();
        System.out.println(movie);
        UIFunctions.divider();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username(Press enter to finish):");
        String name = sc.nextLine();

        System.out.println("Enter review(Press enter to finish): ");
        String review = sc.nextLine();

        Review r = new Review(name, review);
        movie.addReview(r);
        UIFunctions.clear();
        return false;
    }

    /**
     * Calls the showReviews() function in Movie class
     *
     * @param movie
     * @return Sends user up to the previous menu
     * @see UIFunctions
     */
    private static boolean showReviews(Movie movie) {
        UIFunctions.divider();
        System.out.println(movie);
        UIFunctions.divider();
        movie.showReviews();
        UIFunctions.waitForUser();
        UIFunctions.clear();
        return false;
    }

    /**
     * Prompts user to choose movie listing (i.e. cineplex/cinema/datetime combination)
     *
     * @param movie
     * @return true sends user back to mainmenu, false sends user up to the previous menu
     * @see Movie
     */
    private static boolean showListing(Movie movie) {
        UIFunctions.divider();
        System.out.println(movie);
        UIFunctions.divider();
        if(movie.showMovieListings()) {
            System.out.println("No valid movie listings!");
            UIFunctions.waitForUser();
            UIFunctions.clear();
            return false;
        }
        Scanner sc = new Scanner(System.in);
        UIFunctions.divider();
        System.out.print("Choose movie listing: ");
        int c = sc.nextInt();
        UIFunctions.clear();
        if (c > 0) // && c > #movieListings
            return booking(movie, movie.getMovieListing(c));
        return false;
    }

    /**
     * Shows user the cinema layout for seat selection
     *
     * @param movie
     * @param movieListing
     * @return true sends user back to mainmenu, false sends user up to the previous menu
     * @see MovieGoer
     */
    private static boolean booking(Movie movie, MovieListing movieListing) {
        while (true) {
            UIFunctions.divider();
            System.out.println(movie);
            UIFunctions.divider();
            System.out.println(movieListing);
            UIFunctions.divider();
            movieListing.displayCinema();

            Scanner sc = new Scanner(System.in);
            UIFunctions.divider();
            System.out.println("1. Book seats");
            System.out.println("2. Back");
            System.out.println("3. Go to menu");
            System.out.print("Enter option: ");

            int c = sc.nextInt();
            UIFunctions.divider();
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
                        char row = sc.next().toUpperCase().toCharArray()[0];

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

    /**
     * Gets user info to initiate payment process
     *
     * @param rows
     * @param setNrs
     * @param movieListing
     * @see MovieGoer
     * @see Seat
     * @see UIFunctions
     */
    private static void payment(List<Character> rows, List<Integer> setNrs, MovieListing movieListing) {
        UIFunctions.divider();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your phone nummer: ");
        String phoneNumber = sc.nextLine();
        UIFunctions.divider();
        List<MovieGoer> movieGoers = Storage.getUserList();
        MovieGoer user = findMovieGoer(name, movieGoers, phoneNumber, email);
        Seat[] seats = new Seat[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            seats[i] = movieListing.bookSeats(rows.get(i), setNrs.get(i));
        }
        int[] age = new int[seats.length];
        double price = 0;
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Enter age of movie goer " + (i + 1) + " :");
            price += new TicketPrice(sc.nextInt(), movieListing.getMovieType(), movieListing.getCinemaType()).getPrice();
        }

        Booking booking = new Booking(user, movieListing, seats, price);

        user.addBooking(booking);
        Storage.writeUserList(movieGoers);
        UIFunctions.divider();
        System.out.println("Payments done!");
        System.out.println(booking);
        UIFunctions.waitForUser();
        UIFunctions.clear();
    }

    /**
     * Searches for user based on name and phone.
     * If found, returns the object that was found, else we add a new moviegoer.
     *
     * @param name
     * @param movieGoers
     * @param phoneNumber
     * @return MovieGoer object
     * @see MovieGoer
     */
    private static MovieGoer findMovieGoer(String name, List<MovieGoer> movieGoers, String phoneNumber, String email) {
        for (MovieGoer mg : movieGoers) {
            if (name.equals(mg.getName()) && (phoneNumber.equals(mg.getPhone()))) {
                return mg;
            }
        }
        MovieGoer m = new MovieGoer(name, phoneNumber, email);
        movieGoers.add(m);
        return m;
    }

    /**
     * Prompts user to choose between sorting by rating or by sales
     *
     * @see UIFunctions
     */
    private static void listTop5Movies() {
        UIFunctions.divider();
        System.out.println("Show top 5 based on: ");
        System.out.println("1. Overall reviewers' rating");
        System.out.println("2. Ticket Sales: ");
        System.out.println("3. Main menu: ");

        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        UIFunctions.clear();
        UIFunctions.divider();
        if (c == 1) {
            listTop5MoviesRating();
        } else if (c == 2) {
            listTop5TicketSales();
        } else {
            return;
        }
    }

    /**
     * Lists the top 5 movies by user rating.
     * Does so by iterating through MovieList 5 times to find the largest (and excluding them in subsequent iterations)
     * Complexity: 5n vs nlogn by Java's default sorting function (mergesort/quicksort)
     *
     * @see Storage
     * @see Movie
     */
    private static void listTop5MoviesRating() {
        // iterate through movies list and save the top 5 movies into a list 
        List<Movie> movies = Storage.getMovieList();
        listMovies(top5Movies(movies, ratings), true);
        Storage.writeMovieList(movies);
    }

    /**
     * Lists the top 5 movies by ticket sales.
     * Does so by iterating through MovieList 5 times to find the largest (and excluding them in subsequent iterations)
     * Complexity: 5n vs nlogn by Java's default sorting function (mergesort/quicksort)
     *
     * @see Storage
     * @see Movie
     */
    private static void listTop5TicketSales() {
        List<Movie> movies = Storage.getMovieList();
        listMovies(top5Movies(movies, tickets), false);
        Storage.writeMovieList(movies);
    }

    /**
     * Shows user's booking history, retrieved from users.dat
     *
     * @see MovieGoer
     * @see Storage
     * @see UIFunctions
     */
    private static void showBookingHistory() {
        UIFunctions.divider();
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Enter your phone number: ");
        String phone = sc.nextLine();
        UIFunctions.divider();
        for (MovieGoer mg : Storage.getUserList()) {
            if (name.equals(mg.getName()) && phone.equals(mg.getPhone())) {
                mg.showBookingHistory();
                UIFunctions.waitForUser();
                return;
            }
        }
        System.out.println("No booking history for " + name);
        UIFunctions.waitForUser();
    }


    /**
     * List top 5 movies based on ratings or ticket sales
     * @param movies    Movies to list
     * @param comparator comparator to sort movies
     * @return  List with top 5 movies
     */
    public static List<Movie> top5Movies(List<Movie> movies, Comparator<Movie> comparator) {

        List<Movie> copy = new ArrayList<Movie>();
        for (Movie m : movies) {
            copy.add(m);
        }
        copy.sort(comparator);

        List<Movie> top5Movies = new ArrayList<Movie>();
        if(movies.size() < 1) return copy;
        for (int i = 0; i < 5; i++) {
            top5Movies.add(copy.get(i));
        }
        return top5Movies;
    }

    /**
     * Compare movies based on tickets sales
     */
    public static Comparator<Movie> tickets = new Comparator<Movie>() {

        public int compare(Movie o1, Movie o2) {
            return -1*(o1.getTicketSales() - o2.getTicketSales());
        }
    };

    /**
     * Compare movies based on ratings
     */
    public static Comparator<Movie> ratings = new Comparator<Movie>() {
        public int compare(Movie o1, Movie o2) {
            return -1*Double.compare(o1.averageRating(), o2.averageRating());
        }
    };
}
