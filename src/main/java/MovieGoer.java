import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class MovieGoer implements Serializable {
    /**
     * Name of movie goer
     */
    private final String name;
    /**
     * Email of movie goer
     */
    private final String email;
    /**
     * Phone number for movie goer
     */
    private final String phone;
    /**
     * Booking history for movie goer
     */
    private List<Booking> bookingHistory;

    /**
     * @param name  Name of movie goer
     * @param phone Phone number of movie goer
     */
    public MovieGoer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
        bookingHistory = new ArrayList<Booking>();
    }

    /**
     * @param booking   new booking to add to bookinghistory
     * @see Booking
     */
    public void addBooking(Booking booking){
        this.bookingHistory.add(booking);               // Not safe
    }

    /**
     * Show all bookings
     */
    public void showBookingHistory(){
        for(Booking b : bookingHistory)
            System.out.println(b);
    }

    /**
     * Get name of movie goer
     * @return  Name of movie goer
     */
    public String getName() {
        return name;
    }

    /**
     * Get phone for movie goer
     * @return  Phone of movie goer
     */
    public String getPhone() {
        return phone;
    }
}
