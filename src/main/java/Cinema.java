/**
 * Created by Albin on 2017-10-19.
 */
public class Cinema {
    private final String name;
    private Seat[][] seats;
    private final CinemaType type;

    
    /** hi
     * Cinema
     * @param name  Name of cinema
     * @param type  Type of cinema
     * @param numOfRows Number or rows in cinema
     * @param numOfSeats    Number of seats in each row
     * @see CinemaType
     */
    public Cinema(String name, CinemaType type, int numOfRows, int numOfSeats){
        this.name = name;
        this.type = type;
        this.seats = new Seat[numOfRows][numOfSeats];

        // Create all seats
        for (int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfSeats; j++){
                this.seats[i][j] = new Seat(j+1,(char) ('A' + i) );
            }
        }
    }

    public Cinema(Cinema cinema) {
        this.name = cinema.getName();
        this.type = getType();
        this.seats = cinema.getSeats();
    }

    /**
     * Get name of cinema
     * @return Name of cinema
     */
    public String getName() {
        return name;
    }

    /**
     * Display all seats in cinema in stdout
     * @see Seat
     */
    public void displaySeats(){
        System.out.print("\t");
        for (int i = 0; i < seats[0].length; i++){          // Print seat number
            System.out.print(" " + (i+1) + " ");
            if(i == seats[i].length/2 -1 )                  // For middle aisle
                System.out.print("\t");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++){             // Loop trough rows
            System.out.print((char) ('A' + i) + "\t");      // Print row name
            for(int j = 0; j < seats[i].length; j++){       // Loop trough row seats
                System.out.print(this.seats[i][j]);         // Print state of seats
                if(j == seats[i].length/2 - 1)              // For middle aisle
                    System.out.print("   ");
            }
            System.out.println();
        }
    }

    public CinemaType getType() {
        return type;
    }

    public Seat[][] getSeats() {
        Seat[][] copy = new Seat[this.seats.length][this.seats[0].length];
        for (int i = 0; i < this.seats.length; i++){
            for(int j = 0; j < this.seats[0].length; j++){
                copy[i][j] = new Seat(j+1,(char) ('A' + i) );
            }
        }
        return copy;
    }

    public Seat bookSeat(char row, int seat){
        return this.seats[row-'A'][seat-1].mark();
    }

    public boolean isBooked(char row, int seatNr) {
        int r = row - 'A';
        int s = seatNr - 1;

        if(r >= 0 && r < this.seats.length){
            if(s >= 0 && s < this.seats[r].length){
                return !this.seats[r][s].isBooked();
            }
        }
        return false;
    }
}
