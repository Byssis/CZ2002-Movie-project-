/**
 * Created by Albin on 2017-10-19.
 */
public class Seat {
    private final int seatNr;
    private final char row;
    private boolean booked;                 // Not sure of this should be here or in a other class

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

    public boolean mark() {
        if(this.booked) return false;
        this.booked = true;
        return true;
    }

    public boolean isBooked(){
        return this.booked;
    }
}
