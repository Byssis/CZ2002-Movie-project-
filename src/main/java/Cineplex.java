import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class Cineplex implements Serializable {
    private List<Cinema> cinemas;
    final private String name;

    /**
     * Cineplex
     * @param name Name of cineplex
     */
    public Cineplex(String name){
        this.name = name;
        this.cinemas = new ArrayList<Cinema>();
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
    public void addNewCinema(String name, CinemaType type,int numOfRows, int numOfSeats){
        this.cinemas.add(new Cinema(name, type, numOfRows, numOfSeats));
    }

    /**
     * Get name of Cineplex
     * @return Name of Cineplex
     */
    public String getName() {
        return name;
    }
}


