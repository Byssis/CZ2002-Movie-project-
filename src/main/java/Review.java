/**
 * Created by Albin on 2017-10-20.
 */
public class Review {
    private final String name;
    private final String review;

    /**
     * Create ner review
     * @param review    review text
     */
    public Review(String review){
        this.name = "Unknown";
        this.review = review;
    }

    /**
     * @param name  Name of reviewer
     * @param review    Review text
     */
    public Review(String name,String review){
        this.name = name;
        this.review = review;
    }

    public String toString(){
        return this.name + ": \n" + this.review;
    }

    /**
     * Get name of reviewer
     * @return return name of reviewer
     */
    public String getName() {
        return name;
    }

    /**
     * Get review text
     * @return return review text
     */
    public String getReview() {
        return review;
    }
}
