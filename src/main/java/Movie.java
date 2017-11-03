import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.*;
/**
 * Created by Albin on 2017-10-17.
 */
public class Movie implements Serializable {
    /*
        Title of movie
     */
    private String title;
    /*
        Name of director for movie
     */
    private String director;
    /*
        Length of movie in minutes
     */
    private int duration;
    /*
        List of cast in movie
     */
    private ArrayList<String> cast;
    /*
        Type of movie
     */
    private Type type;
    /*
        Start showing date for movie
     */
    private String startDate;
    /*
        End showing date for movie
     */
    private String endDate;
    /*
        Number of tickets sold for movie
     */
    private int ticketSales;
    /*
        List of ratings for movie
     */
    private List<Rating> ratings;
    /*
        List of reviews for movie
     */
    private List<Review> reviews;
    /*
        List of movieListings for movie
     */
    private List<MovieListing> movieListings;

    /**
     * Create a new movie instance
     *
     * @param title     Title of movie
     * @param director  Name of director
     * @param duration  Duration of movie in minutes
     * @param cast      List of cast
     * @param type      Type of movie
     * @param startDate First day of showing
     * @param endDate   Last day of showing
     */
    public Movie(String title, String director, int duration, ArrayList<String> cast, Type type, String startDate, String endDate) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.type = type;
        this.startDate = startDate;                             // Not safe
        this.endDate = endDate;                                 // Not safe
        this.cast = new ArrayList<String>();
        this.ticketSales = 0;
        for (int i = 0; i < cast.size(); i++) {
            this.cast.add(cast.get(i));
        }
        ratings = new ArrayList<Rating>();
        reviews = new ArrayList<Review>();
        movieListings = new ArrayList<MovieListing>();
    }

    /**
     * String representation for the movie
     *
     * @return String representation for the movie
     */
    public String toString() {
        StringBuilder out = new StringBuilder();                // Stringbuilder to build out string
        System.out.println();
        out.append("\nTitle:\t\t" + this.title + "\n");          // Append tittle
        out.append("Rating:\t\t" + this.averageRatingStr() + "\n");
        out.append("Director:\t" + this.director + "\n");       // Append Director
        out.append("Duration:\t" + this.duration + "\n");  // Append Duration of movie
        out.append("Start Date : \t" + this.startDate);
        out.append("\nEnd Date : \t" + this.endDate);
        out.append("\nCast:");
        for (String c : this.cast) {                             // Go through all cast names
            out.append("\t\t" + c);                         // Append cast
        }
        out.append("\nType:\t\t");                              // Append type of movie
        switch (type) {
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
     * Display movie information in stdout
     */
    public void displayMovieInfo() {
        System.out.println(this.toString());
    }

    /**
     * Add a new rating to movie
     *
     * @param rating Rating of movie
     * @see Rating
     */
    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void showReviews() {
        if (reviews.size() == 0)
            System.out.println("No reviews available for movie...");
        for (Review r : reviews) {
            System.out.println(r);
            UIFunctions.divider();
        }
    }

    /**
     * Calculate average rating
     *
     * @return Average of all rating for movie
     */
    public double averageRating() {
        if (this.ratings.size() == 0) return 0;
        double sum = 0.0;                                       // Variable to store sum
        for (Rating r : this.ratings)                            // Go trough all ratings
            sum += r.getRating();                               // Add rating
        return sum / this.ratings.size();                       // divide with num of ratings to get average
    }


    /**
     * Get title of movie
     *
     * @return Title of movie
     */
    public String getTitle() {
        return this.title;
    }
    
    /* Set Title Name*/

    public void setTitle(String newtitle) {
        this.title = newtitle;
    }
    
    /* Set Director Name*/

    public void setDirectorname(String directorname) {
        director = directorname;
    }
    
    /* Set Duration */

    public void setDuration(int minutes) {
        duration = minutes;
    }

    /* update cast member - doing for one cast only */
    /* since we will not update a whole list of cast */
    public void setCast(String casts, int k) {
        cast.set(k, casts);
    }

    /* Add cast members in */
    public void addCast(String casts) {
        int i;
        for (i = 0; i < cast.size(); i++) {
            cast.add(casts);
        }
    }

    /* Remove cast members in */
    public void removeCast(String casts) {
        if (cast.contains(casts))
            cast.remove(casts);
        else
            System.out.println("Cast entered is invalid");
    }

    /* Return the array of casts' names */
    public ArrayList<String> getCast() {
        return this.cast;
    }

    /* Return the type of the movie */
    public Type getType() {
        return this.type;
    }
    
    /* Set the type of the movie */

    public void setType(int option) {
        if (option == 1)
            type = Type.TREED;
        else if (option == 2)
            type = Type.NORMAL;
        else if (option == 3)
            type = Type.BLOCKBUSTER;
        else
            System.out.println("Invalid value entered");
    }

    /* Set the start date of the movie */
    public void setStartDate(String startdate) {
        if (isValidDate(startdate)) {
            startDate = startdate;
        } else
            System.out.println("Invalid date or date format entered");
    }

    /* Set the end date of the movie */
    public void setEndDate(String enddate) {
        if (isValidDate(enddate)) {
            endDate = enddate;
        } else
            System.out.println("Invalid date or date format entered");
    }

    /**
     * Add movieListing for movie
     *
     * @param cineplex At Cineplex
     * @param cinema   In Cinema
     * @param showing  Showing date
     * @see MovieListing
     */
    public void addMovieListing(Cineplex cineplex, Cinema cinema, Date showing) {
        this.movieListings.add(new MovieListing(this, cineplex, cinema, showing));
    }

    /**
     * Show all movieListings in stdout
     */
    public void showMovieListings() {
        for (int i = 0; i < movieListings.size(); i++) {
            System.out.println((i + 1) + ": " + movieListings.get(i));
        }
    }

    /**
     * Get movieListing from index
     *
     * @param index Index for movieListing
     * @return Movie Listings at index
     * @see MovieListing
     */
    public MovieListing getMovieListing(int index) {
        return movieListings.get(index - 1);
    }

    /**
     * Add one ticket sale to movie
     */
    public void addTicketSales() {
        ticketSales++;
    }

    /**
     * Get number of ticket sales for movie
     *
     * @return Number of ticket sales
     */
    public int getTicketSales() {
        return this.ticketSales;
    }


    public static boolean isValidDate(String inDate) {                        //check if the date passed in is a valid one
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public String averageRatingStr() {
        if(this.ratings.size() == 0)
            return "NA";
        return "" +this.averageRating();
    }
}

