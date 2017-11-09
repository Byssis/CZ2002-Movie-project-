import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class Cineplex implements Serializable {
    /*
        List of cinemas in cineplex
     */
    private ArrayList<Cinema> cinemas;
    /*
        Name of cineplex
     */
    final private String name;

    final private String cineplexCode;

    /**
     * Cineplex
     * @param name Name of cineplex
     */
    public Cineplex(String name, String cineplexCode){
        this.name = name;
        this.cinemas = new ArrayList<Cinema>();
        this.cineplexCode = cineplexCode;
    }

    /**
     * Create new cinema in cineplex
     * @param name  Name of cinema to add
     * @param type  Type of new cinema
     * @param numOfRows Number of rows in new cinema
     * @param numOfSeats    Number of seats in each row in new cinema
     * @see Cinema
     * @see CinemaType
     */
    public void addNewCinema(String name, CinemaType type,int numOfRows, int numOfSeats, String cinemaCode){
        this.cinemas.add(new Cinema(name, type, numOfRows, numOfSeats, cinemaCode));
    }
    
    public Cinema getCinema(int i) {
    	return cinemas.get(i);
    }

    /**
     * Get name of Cineplex
     * @return Name of Cineplex
     */
    public String getName() {
        return name;
    }

    public String getCineplexCode() {
        return cineplexCode;
    }
}


