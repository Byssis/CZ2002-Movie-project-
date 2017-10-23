/**
 * Created by Albin on 2017-10-20.
 */
public class Review {
    private final String name;
    private final String review;

    public Review(String review){
        this.name = "";
        this.review = review;
    }

    public Review(String name,String review){
        this.name = "";
        this.review = review;
    }

    public String toString(){
        return this.name + ": \n" + this.review;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }
}
