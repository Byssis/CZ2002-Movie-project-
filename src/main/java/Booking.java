/**
 * Created by Albin on 2017-10-19.
 */
public class Booking {
    private final MovieGoer movieGoer;
    private final MovieListing movieListing;
    private final Seat[] seats;

    /**
     * @param movieGoer Movie goer that booked ticket
     * @param movieListing  Booked movie listing
     * @param seats booked seats
     */
    public Booking(MovieGoer movieGoer, MovieListing movieListing, Seat[] seats){
        this.movieGoer = movieGoer;
        this.movieListing = movieListing;
        this.seats = seats;
    }

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
