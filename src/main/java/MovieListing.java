import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class MovieListing implements Serializable {
    /*
        Listed movie
     */
    final private Movie movie;
    /*
        Cineplex for listing
     */
    final private Cineplex cineplex;
    /*
        Cinema for listing
     */
    final private Cinema cinema;
    /*
        Showing date for movie
     */
    final private String showing;
    /*
        List of bookings for movie
     */
    private List<Booking> bookings;

    /**
     * @param movie     Listed movie
     * @param cineplex  Cineplex where movie is shown
     * @param cinema    Cinema where movie is shown
     * @param showing   Start time for movie
     */
    public MovieListing(Movie movie, Cineplex cineplex, Cinema cinema, String showing){
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
        this.movie.addTicketSales();
        return this.cinema.bookSeat(startRow, startSeat);
    }

    /**
     * String representation for movie
     * @return  String representation for movie
     */
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

    public String getCinemaCode() {
        return this.cineplex.getCineplexCode() + this.cinema.getCinemaCode();
    }

    public Type getMovieType() {
        return this.movie.getType();
    }

    public String getShowing(){
        return this.showing;
    }

    public CinemaType getCinemaType() {
        return this.cinema.getType();
    }
}
