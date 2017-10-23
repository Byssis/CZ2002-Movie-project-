import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Albin on 2017-10-17.
 */
public class Movie {
    final private String title;
    final private String director;
    final private int duration;
    final private String[] cast;
    final private Type type;
    final private Date startDate;
    final private Date endDate;

    private List<Rating> ratings;
    private List<Review> reviews;
    private List<MovieListing> movieListings;

    /**
     * Create a new movie instance
     * @param title     Title of movie
     * @param director  Name of director
     * @param duration  Duration of movie in minutes
     * @param cast      List of cast
     * @param type      Type of movie
     * @param startDate First day of showing
     * @param endDate   Last day of showing
     */
    public Movie(String title, String director, int duration, String[] cast, Type type, Date startDate, Date endDate){
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.type = type;
        this.startDate = startDate;                             // Not safe
        this.endDate = endDate;                                 // Not safe
        this.cast = new String[cast.length];
        for(int i = 0; i< cast.length; i++){
            this.cast[i] = cast[i];
        }
        ratings = new ArrayList<Rating>();
        reviews = new ArrayList<Review>();
        movieListings = new ArrayList<MovieListing>();
    }

    /**
     * String representation for the movie
     * @return      String representation for the movie
     */
    public String toString(){
        StringBuilder out = new StringBuilder();                // Stringbuilder to build out string
        out.append("Tittle:\t\t" + this.title + "\n");          // Append tittle
        out.append("Rating:\t\t" + this.averageRating() + "\n");
        out.append("Director:\t" + this.director + "\n");       // Append Director
        out.append("Duration:\t" + this.duration + "\n");       // Append Duration of movie
        out.append("Cast:");
        for (String c : this.cast){                             // Go through all cast names
            out.append("\n\t\t\t" + c);                         // Append cast
        }
        out.append("\nType:\t\t");                              // Append type of movie
        switch (type){
            case TREED:
                out.append("3D");
                break;
            case BLOCKBUSTER:
                out.append("Blockbuster");
                break;
            case NORMAL:
                out.append("Normal");
                break;
        }
        return out.toString();
    }

    /**
     *  Display movie information in stdout
     */
    public void displayMovieInfo(){
        System.out.println(this.toString());
    }

    /**
     * Add a new rating to movie
     * @param rating    Rating of movie
     * @see Rating
     */
    public void addRating(Rating rating){
        this.ratings.add(rating);                               // Not safe
    }

    public void addReview(Review review){
        this.reviews.add(review);                               // Not safe
    }

    public void showReviews(){
        if (reviews.size() == 0)
            System.out.println("No reviews available for movie...");
        for(Review r : reviews) {
            System.out.println(r);
            UIFunctions.divder();
        }
    }

    /**
     * Calculate average rating
     * @return      Average of all rating for movie
     */
    public double averageRating(){
        if(this.ratings.size() == 0) return 0;
        double sum = 0.0;                                       // Variable to store sum
        for(Rating r : this.ratings)                            // Go trough all ratings
            sum += r.getRating();                               // Add rating
        return sum / this.ratings.size();                       // divide with num of ratings to get average
    }


    /**
     * @return      get movie title
     */
    public String getTitle(){
        return this.title;
    }

    public void addMovieListing(Cineplex cineplex, Cinema cinema, Date showing){
        this.movieListings.add(new MovieListing(this, cineplex, cinema, showing));
    }

    public void showMovieListings(){
        for(int i = 0; i < movieListings.size(); i++){
            System.out.println((i+1)+ ": " + movieListings.get(i));
        }
    }

    public MovieListing getMovieListing(int index){
        return movieListings.get(index-1);
    }

    /**
     * @return Type of movie
     */
    public Type getType(){
        return this.type;
    }
}
