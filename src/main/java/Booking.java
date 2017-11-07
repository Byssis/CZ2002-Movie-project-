import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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

    private final String transactionID;

    private final double price;

    /**
     * Create new booking
     * @param movieGoer Movie goer that booked ticket
     * @param movieListing  Booked movie listing
     * @param seats booked seats
     * @param price
     * @see MovieGoer
     * @see MovieListing
     */
    public Booking(MovieGoer movieGoer, MovieListing movieListing, Seat[] seats, double price){
        this.movieGoer = movieGoer;
        this.movieListing = movieListing;
        this.seats = seats;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append(this.movieListing.getCinemaCode()+ "\n");
        sb.append(cal.get(Calendar.YEAR));
        sb.append(cal.get(Calendar.MONTH));
        sb.append(cal.get(Calendar.DAY_OF_MONTH));
        sb.append(cal.get(Calendar.HOUR_OF_DAY));
        sb.append(cal.get(Calendar.MINUTE));
        this.transactionID = sb.toString();
        this.price = price;
    }

    /**
     * String representation for booking
     * @return  String representation for booking
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tid:" + this.transactionID);
        sb.append("Name:" + this.movieGoer.getName());
        sb.append(", Movie:" + this.movieListing.getMovieTitle());
        sb.append(this.movieListing + "\n");
        for (Seat s: seats){
            sb.append(" " + s.getRow() + "," + s.getSeatNr());
        }
        sb.append("\nPrice:" + this.price);
        return sb.toString();
    }

}
