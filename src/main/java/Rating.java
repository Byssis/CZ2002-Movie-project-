/**
 * Created by Albin on 2017-10-17.
 */
public class Rating {
    final private String userName;
    final private int rating;

    /**
     *
     * @param userName  Name of user who rated movie
     * @param rating    Rating score of movie
     * @see Movie
     */
    public Rating(String userName, int rating){
        this.userName = userName;
        this.rating = rating;
    }

    /**
     * Get name of user who rated movie
     * @return      Name of user
     */
    public String getName() {
        return userName;
    }

    /**
     * Get rating value
     * @return      Rating score
     */
    public int getRating() {
        return rating;
    }

    /**
     * String representation for rating
     * @return      String representation for rating
     */
    public String toString(){
        return this.userName + ": " + rating;
    }
}
