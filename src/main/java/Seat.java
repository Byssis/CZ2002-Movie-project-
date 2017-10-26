import java.io.Serializable;

/**
 * Created by Albin on 2017-10-19.
 */
public class Seat implements Serializable {

    /*
        Seat number for seat
     */
    private final int seatNr;
    /*
        Row letter for seat
     */
    private final char row;
    /*
        If seat is booked
     */
    private boolean booked;

    /**
     * Cinema seat
     * @param seatNr    Seat number
     * @param row       Row letter
     */
    public Seat(int seatNr, char row){
        this.seatNr = seatNr;
        this.row = row;
        this.booked = false;
    }


    /**
     * String representation of seat
     * @return      String representation of seat
     */
    public String toString(){
        if(booked)
            return "[X]";
        else
            return "[ ]";
    }

    /**
     * Mark seat booked
     * @return  booked seat
     */
    public Seat mark() {
        if(this.booked) return null;
        this.booked = true;
        return this;
    }

    /**
     * Check if seat ia booked
     * @return  if seat is booked
     */
    public boolean isBooked(){
        return this.booked;
    }

    /**
     * Get seat number of seat
     * @return  Seat number
     */
    public int getSeatNr() {
        return seatNr;
    }

    /**
     * Get row letter
     * @return Row letter
     */
    public char getRow() {
        return row;
    }
}
