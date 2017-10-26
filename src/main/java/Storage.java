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


    private static void checkFile(String path){
        File f = new File(path);
        if(!f.exists()) {

        }
    }

    public static List<Movie> getMovieList()  {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(MOVIE_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<Movie>();
        }
    }

    public static void writeMovieList(List movies){
        SerializeDB.writeSerializedObject(MOVIE_LIST_FILENAME, movies);
    }

    public static List<MovieGoer> getUserList() {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(USER_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<MovieGoer>();
        }
    }

    public static void writeUserList(List users){
        SerializeDB.writeSerializedObject(USER_LIST_FILENAME, users);
    }

    public static List<Cineplex> getCineplexList() {
        try {
            return (ArrayList)SerializeDB.readSerializedObject(CINEPLEX_LIST_FILENAME);
        } catch (IOException e) {
            return new ArrayList<Cineplex>();
        }
    }

    public static void writeCineplexList(List cineplex){
        SerializeDB.writeSerializedObject(CINEPLEX_LIST_FILENAME, cineplex);
    }

    public static void main(String[] args){
        List<Movie> movies = new ArrayList();
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
        writeMovieList(movies);
    }

}
