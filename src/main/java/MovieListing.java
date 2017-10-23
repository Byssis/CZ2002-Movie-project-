import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class MovieListing {
    final private Movie movie;
    final private Cineplex cineplex;
    final private Cinema cinema;
    final private Date showing;
    private List<Booking> bookings;

    /**
     * @param movie     Listed movie
     * @param cineplex  Cineplex where movie is shown
     * @param cinema    Cinema where movie is shown
     * @param showing   Start time for movie
     */
    public MovieListing(Movie movie, Cineplex cineplex, Cinema cinema, Date showing){
        this.movie = movie;
        this.cineplex = cineplex;
        this.cinema = new Cinema(cinema);
        this.showing = showing;
        this.bookings = new ArrayList<Booking>();
    }

    /**
     * Display available seats
     */
    public void displayCinema(){
        this.cinema.displaySeats();
    }

    /**
     * Book seats
     * @param startRow  Row of seats
     * @param startSeat Seat number in row
     * @return  Return true if seat where booked
     */
    public Seat bookSeats(char startRow, int startSeat){
        return this.cinema.bookSeat(startRow, startSeat);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        //sb.append("Movie: ");
        //sb.append(this.movie.getTitle());
        sb.append("\n\tCineplex:\t");
        sb.append(this.cineplex.getName());
        sb.append("\n\tCinema:\t\t");
        sb.append(this.cinema.getName());
        sb.append("\n\tTime:\t\t");
        sb.append(this.showing);

        return sb.toString();
    }

    /**
     * @param row       Row of seats
     * @param seatNr    Seat number in row
     * @return          if seat is available or not
     */
    public boolean seatAvailable(char row, int seatNr) {
        return this.cinema.isBooked(row, seatNr);
    }

    /**
     * Get movie title for listing
     * @return  return movie title
     */
    public String getMovieTitle(){
        return this.movie.getTitle();
    }
}
