import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-10-19.
 */
public class MovieGoer implements Serializable {
    private final String name;
    private final String phone;
    private List<Booking> bookingHistory;

    /**
     * @param name  Name of movie goer
     * @param phone Phone number of movie goer
     */
    public MovieGoer(String name, String phone){
        this.name = name;
        this.phone = phone;
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
     * @return  Name of movie goer
     */
    public String getName() {
        return name;
    }

    /**
     * @return  Phone of movie goer
     */
    public String getPhone() {
        return phone;
    }
}
