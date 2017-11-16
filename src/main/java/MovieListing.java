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
    private Cineplex cineplex;
    /*
        Cinema for listing
     */
    private Cinema cinema;
    /*
        Showing date for movie
     */
    private String showing;
    /*
        List of bookings for movie
     */
    private List<Booking> bookings;
    
    private Storage storage;

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
    
    public void setCineplex(String cineplex) 
    {
    	ArrayList<Cineplex> cineplexlist = new ArrayList<Cineplex>();
    	cineplexlist = Storage.getCineplexList();
    	int i;
    	for (i=0; i<3 ;i++) {
    		if (cineplexlist.get(i).getName().equals(cineplex))
    			this.cineplex = cineplexlist.get(i);
    	}
    	
    }
    
    public void setCinema(String cinema) 
    {
    	ArrayList<Cineplex> cineplexlist = new ArrayList<Cineplex>();
    	cineplexlist = Storage.getCineplexList();
    	int i;
    	for (i=0; i<3 ;i++) 
    	{
    		if (cineplexlist.get(0).getCinema(i).getName().equals(cinema))
    				this.cinema = cineplexlist.get(0).getCinema(i);
    	}
    	
    }
    public void setShowing(String showing) 
    {
    	this.showing = showing;
    	
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
        sb.append(" "+this.cinema.getTypeString());
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
    
    public String getCineplexName() {
    	return this.cineplex.getName();
    }
    public String getCinemaName() {
    	return this.cinema.getName();
    }
    public String getShowing() {
    	return showing;
    }
    
    public Type getMovieType() {
        return this.movie.getType();
    }

    public CinemaType getCinemaType() {
        return this.cinema.getType();
    }
}
