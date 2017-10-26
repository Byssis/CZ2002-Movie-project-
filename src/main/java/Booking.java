import java.io.Serializable;

/**
 * Created by Albin on 2017-10-19.
 */
public class Booking implements Serializable {
    /*
        Reference to movieGoer
     */
    private final MovieGoer movieGoer;
    /*
        Reference to movieListing
     */
    private final MovieListing movieListing;
    /*
        Booked seats
     */
    private final Seat[] seats;

    /**
     * Create new booking
     * @param movieGoer Movie goer that booked ticket
     * @param movieListing  Booked movie listing
     * @param seats booked seats
     * @see MovieGoer
     * @see MovieListing
     */
    public Booking(MovieGoer movieGoer, MovieListing movieListing, Seat[] seats){
        this.movieGoer = movieGoer;
        this.movieListing = movieListing;
        this.seats = seats;
    }

    /**
     * String representation for booking
     * @return  String representation for booking
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name:" + this.movieGoer.getName());
        sb.append(", Movie:" + this.movieListing.getMovieTitle());
        sb.append(this.movieListing + "\n");
        for (Seat s: seats){
            sb.append(" " + s.getRow() + "," + s.getSeatNr());
        }
        return sb.toString();
    }

}
