/**
 * Created by Albin on 2017-10-19.
 */
public class Cinema {
    private final String name;
    private Seat[][] seats;
    private CinemaType type;

    /**
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
        for (int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfSeats; j++){
                this.seats[i][j] = new Seat(j+1,(char) ('A' + i) );
            }
        }
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
        for (int i = 0; i < seats[0].length; i++){
            System.out.print(" " + (i+1) + " ");
            if(i == seats[i].length/2 -1 )
                System.out.print("\t");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++){
            System.out.print((char) ('A' + i) + "\t");
            for(int j = 0; j < seats[i].length; j++){
                System.out.print(this.seats[i][j]);
                if(j == seats[i].length/2 - 1)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }
}
