import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Albin on 2017-10-26.
 */
public class Storage {
    private final static String FILE_PATH = "";
    private final static String MOVIE_LIST_FILENAME = FILE_PATH + "movies.dat";
    private final static String USER_LIST_FILENAME = FILE_PATH + "users.dat";
    private final static String CINEPLEX_LIST_FILENAME = FILE_PATH + "cineplex.dat";
    private final static String MOVIE_LISTING_FILENAME = FILE_PATH + "movielisting.dat";

    public final static String USER_ACC_FILENAME =  FILE_PATH + "useracc.dat";
    public final static String HOLIDAYS_FILENAME = FILE_PATH + "pubhol.dat";



    public static ArrayList<String> getHolidays(){
        try {
            return (ArrayList)SerializeDB.readSerializedObject(HOLIDAYS_FILENAME);
        } catch (IOException e) {
            return new ArrayList<String>();
        }
    }

    public static void writeHolidays(List holidays){
        SerializeDB.writeSerializedObject(HOLIDAYS_FILENAME, holidays);
    }

    public static ArrayList<String> getAdmins(){
        try {
            return (ArrayList)SerializeDB.readSerializedObject(USER_ACC_FILENAME);
        } catch (IOException e) {
            ArrayList<String> defaults = new ArrayList<String>();
            defaults.add("user123");
            defaults.add("password123");
            return defaults;
        }
    }

    /**
     * @param admins
     */
    public static void writeAdmins(List admins){
        SerializeDB.writeSerializedObject(USER_ACC_FILENAME, admins);
    }

    /**
     * Get list of movies from memory
     * @return  Return saved list of movies
     * @see Movie
     */
    public static ArrayList<Movie> getMovieList()  {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(MOVIE_LISTING_FILENAME);
        } catch (IOException e) {
            return new ArrayList<Movie>();
        }
    }
    
    public static ArrayList<MovieListing> getMovieListing()  {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(MOVIE_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<MovieListing>();
        }
    }
    /**
     * Save list of movies to memory
     * @param movies List of movies to save
     * @see Movie
     */
    public static void writeMovieList(List movies){
        SerializeDB.writeSerializedObject(MOVIE_LIST_FILENAME, movies);
    }
    public static void writeMovieListing(List movielisting){
        SerializeDB.writeSerializedObject(MOVIE_LISTING_FILENAME, movielisting);
    }
    /**
     * Get list of users from memory
     * @return  List of user from memory
     * @see MovieGoer
     */
    public static List<MovieGoer> getUserList() {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(USER_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<MovieGoer>();
        }
    }

    /**
     * Save list of users
     * @param users List of users to save
     * @see MovieGoer
     */
    public static void writeUserList(List users){
        SerializeDB.writeSerializedObject(USER_LIST_FILENAME, users);
    }

    /**
     * Get list of cineplex from memory
     * @return List of cineplex from memory
     * @see Cineplex     *
     */
    public static List<Cineplex> getCineplexList() {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(CINEPLEX_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<Cineplex>();
        }
    }


    /**
     * Save list of cineplex to memory
     * @param cineplex  List of cineplex to save
     * @see Cineplex
     */
    public static void writeCineplexList(List cineplex){
        SerializeDB.writeSerializedObject(CINEPLEX_LIST_FILENAME, cineplex);
    }

    public static void main(String[] args){
        /*
            For test
         */
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<String> cast = new ArrayList<String>();
        ArrayList<MovieListing> movielisting = new ArrayList<MovieListing>();
        cast.add("Albin");
        cast.add("Andrew");
        cast.add("Darien");
        cast.add("Yihao");

        movies.add(new Movie("Die Hard", "A director", "90", cast, Type.BLOCKBUSTER, "2017-05-12", "2017-06-20","Abstract 1"));
        movies.add(new Movie("Finding Nemo", "B director", "100", cast, Type.BLOCKBUSTER, "2017-10-10", "2017-11-20","Astract 2"));
        movies.add(new Movie("Planet of the apes", "C director", "120", cast, Type.BLOCKBUSTER, "2017-20-11", "2017-05-20","Abstract 3"));
        movies.add(new Movie("Spiderman", "D director", "130", cast, Type.BLOCKBUSTER, "2017-17-11", "2017-03-03","Abstract 4"));
        movies.add(new Movie("Superman", "E director", "140", cast, Type.BLOCKBUSTER, "2017-01-01", "2017-02-02","Abstract 5"));
        movielisting.add(new MovieListing(movies.get(0),new Cineplex("Golden Village", "XX1"),new Cinema("Imax 1",CinemaType.NORMAL, 10, 10, "XXX1"), "2017-05-12"));
        movielisting.add(new MovieListing(movies.get(1),new Cineplex("Golden Village", "XX2"),new Cinema("Imax 2",CinemaType.PLATINUM, 10, 10, "XXX2"), "2017-10-10"));
        movielisting.add(new MovieListing(movies.get(2),new Cineplex("Cathay", "XX3"),new Cinema("Imax 3",CinemaType.NORMAL, 10, 10, "XXX3"), "2017-20-11"));
        movielisting.add(new MovieListing(movies.get(3),new Cineplex("Cathay", "XX4"),new Cinema("Imax 4",CinemaType.NORMAL, 10, 10, "XXX4"), "2017-17-11"));
        movielisting.add(new MovieListing(movies.get(4),new Cineplex("Xin Hua", "XX5"),new Cinema("Imax 5",CinemaType.PLATINUM, 10, 10, "XXX5"), "2017-10-10"));

        writeMovieList(movies);
        writeMovieListing(movielisting);
        
    }

}
