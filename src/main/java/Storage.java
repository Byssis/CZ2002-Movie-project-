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
            return (ArrayList)SerializeDB.readSerializedObject(MOVIE_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<Movie>();
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
    public static ArrayList<Cineplex> getCineplexList() {
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
    public static void writeCineplexList(ArrayList cineplex){
        SerializeDB.writeSerializedObject(CINEPLEX_LIST_FILENAME, cineplex);
    }

    public static void main(String[] args){
        /*
            For test
         */
    	ArrayList<String> holidays = new ArrayList<String>();
    	holidays.add("2017-01-01");
    	holidays.add("2017-01-28");
    	holidays.add("2017-01-29");
    	holidays.add("2017-04-14");
    	holidays.add("2017-05-01");
    	holidays.add("2017-05-10");
    	holidays.add("2017-06-25");
    	holidays.add("2017-08-09");
    	holidays.add("2017-09-01");
    	holidays.add("2017-10-18");
    	holidays.add("2017-12-25");
    	writeHolidays(holidays);
    	
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<String> cast = new ArrayList<String>();
        ArrayList<Cineplex> cineplexlist = new ArrayList<Cineplex>();
        cast.add("Albin");
        cast.add("Andrew");
        cast.add("Darien");
        cast.add("Yihao");
        Cineplex cineplex1 = new Cineplex("Cineplex 1","XX1");
        cineplex1.addNewCinema("IMAX 1",CinemaType.PLATINUM, 10, 10, "XXX1");
        cineplex1.addNewCinema("IMAX 2",CinemaType.PLATINUM, 10, 10, "XXX2");
        cineplex1.addNewCinema("IMAX 3",CinemaType.NORMAL, 10, 10, "XXX3");
        Cineplex cineplex2 = new Cineplex("Cineplex 2","XX2");
        cineplex2.addNewCinema("IMAX 1",CinemaType.PLATINUM, 10, 10, "XXX1");
        cineplex2.addNewCinema("IMAX 2",CinemaType.PLATINUM, 10, 10, "XXX2");
        cineplex2.addNewCinema("IMAX 3",CinemaType.NORMAL, 10, 10, "XXX3");
        Cineplex cineplex3 = new Cineplex("Cineplex 3","XX3");
        cineplex3.addNewCinema("IMAX 1",CinemaType.PLATINUM, 10, 10, "XXX1");
        cineplex3.addNewCinema("IMAX 2",CinemaType.PLATINUM, 10, 10, "XXX2");
        cineplex3.addNewCinema("IMAX 3",CinemaType.NORMAL, 10, 10, "XXX3");
        cineplexlist.add(cineplex1);
        cineplexlist.add(cineplex2);
        cineplexlist.add(cineplex3);
        writeCineplexList(cineplexlist);
        // put 3 cinema into array then add into cinplex 1,2 and 3 each //
        
        movies.add(new Movie("Die Hard", "A director", "90", cast, Type.BLOCKBUSTER, "2017-11-11", "2017-12-12","Abstract 1","PG"));
        movies.add(new Movie("Finding Nemo", "B director", "100", cast, Type.BLOCKBUSTER, "2017-11-11", "2017-12-12","Astract 2","PG"));
        movies.add(new Movie("Planet of the apes", "C director", "120", cast, Type.BLOCKBUSTER, "2017-11-11", "2017-12-12","Abstract 3","PG"));
        movies.add(new Movie("Spiderman", "D director", "130", cast, Type.BLOCKBUSTER, "2017-11-20", "2017-12-12","Abstract 4","PG"));
        movies.add(new Movie("Superman", "E director", "140", cast, Type.BLOCKBUSTER, "2017-11-22", "2017-12-12","Abstract 5","PG"));
        movies.get(0).addMovieListing(cineplex1, cineplex1.getCinema(0) ,  "2017-05-12 | 19:00");
        movies.get(0).addMovieListing(cineplex1, cineplex1.getCinema(1) ,  "2017-05-12 | 19:00");
        movies.get(0).addMovieListing(cineplex1, cineplex1.getCinema(2) ,  "2017-05-12 | 19:00");
        movies.get(1).addMovieListing(cineplex2, cineplex2.getCinema(0),  "2017-10-10 | 18:00");
        movies.get(1).addMovieListing(cineplex2, cineplex2.getCinema(1),  "2017-10-10 | 18:00");
        movies.get(1).addMovieListing(cineplex2, cineplex2.getCinema(2),  "2017-10-10 | 18:00");
        movies.get(2).addMovieListing(cineplex3,cineplex3.getCinema(0),  "2017-10-12 | 17:00");
        movies.get(2).addMovieListing(cineplex3, cineplex3.getCinema(1),  "2017-10-12 | 17:30");
        movies.get(2).addMovieListing(cineplex3, cineplex3.getCinema(2),  "2017-10-12 | 17:30");
        movies.get(3).addMovieListing(cineplex1,cineplex1.getCinema(0),  "2017-17-11 | 16:00");
        movies.get(3).addMovieListing(cineplex2,cineplex2.getCinema(1),  "2017-17-11 | 17:30");
        movies.get(3).addMovieListing(cineplex3,cineplex3.getCinema(2),  "2017-17-11 | 17:30");
        movies.get(4).addMovieListing(cineplex1, cineplex1.getCinema(0),  "2017-10-10 | 17:30");
        movies.get(4).addMovieListing(cineplex2, cineplex2.getCinema(1),  "2017-10-10 | 17:30");
        movies.get(4).addMovieListing(cineplex3, cineplex3.getCinema(2),  "2017-10-10 | 17:30");
        writeMovieList(movies);
        
    }

}
