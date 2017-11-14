import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.*;
/**
 * Created by Albin on 2017-10-17.
 */
public class Movie implements Serializable {
    
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDayCount(String start, String end) {
	  long diff = -1;
	  try {
	    Date dateStart = simpleDateFormat.parse(start);
	    Date dateEnd = simpleDateFormat.parse(end);

	    //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
	    diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
	  } catch (Exception e) {
	    //handle the exception according to your own situation
	  }
	  return diff;
	}

	/*
     	Status of movie
     */
	private MovieStatus status;
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
    private String duration;
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
    	Abstract for movie
     */
    private String movieAbstract;
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
    private ArrayList<MovieListing> movielistings;

    private MovieStatus moviestatus;
    
    private String movieclassification;
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
     * @para movieclassification PG/NC16/M18/R21
     */
    public Movie(String title, String director, String duration, ArrayList<String> cast, Type type, String startDate, String endDate, String movieAbstract,String classification) {
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
        this.movieclassification = classification;
        ratings = new ArrayList<Rating>();
        reviews = new ArrayList<Review>();
        movielistings = new ArrayList<MovieListing>();
        this.movieAbstract = movieAbstract;
        findStatus();
    }
    
    //findStatus
    public void findStatus(){
    	Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormatter.format(now);
		if(date.compareTo(startDate)>0 && date.compareTo(endDate)<0){
			this.status = MovieStatus.NOW_SHOWING;
		}else if(date.compareTo(endDate)>0){
			this.status = MovieStatus.END_OF_SHOWING;
		}else if(getDayCount(date,startDate)>7){
			this.status = MovieStatus.COMING_SOON;
		}else{
			this.status = MovieStatus.PREVIEW;
		}	
    }
    
    //setStatus
    public void setStatus(String option){
    	if(option.equals("1"))
			this.status = MovieStatus.PREVIEW;
    	else if (option.equals("2"))
			this.status = MovieStatus.COMING_SOON;
    	else if (option.equals("3"))
    		this.status = MovieStatus.NOW_SHOWING;
    	else{
    		this.status = MovieStatus.END_OF_SHOWING;
    	}
    	
    }
    
    //getStatus
    public MovieStatus getStatus(){
    	return this.status;
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
        out.append("Abstract: \t" + this.movieAbstract + "\n" );
        out.append("Duration:\t" + this.duration + "\n");  // Append Duration of movie
        out.append("Rating : \t" + this.movieclassification + "\n");
        out.append("Start Date : \t" + this.startDate);
        out.append("\nEnd Date : \t" + this.endDate);
        out.append("\nCast:");
        for (String c : this.cast) {                             // Go through all cast names
            out.append("\t\t" + c);                         // Append cast
        }
        out.append("\nStatus : \t");
        switch(status){
        	case PREVIEW:
        		out.append("PREVIEW");
        		break;
        	case COMING_SOON:
        		out.append("COMING SOON");
        		break;        	
        	case NOW_SHOWING:
        		out.append("NOW SHOWING");
        		break;
        	case END_OF_SHOWING:
        		out.append("END OF SHOWING");
        		break;
        
        }
        
        out.append("\nType:\t\t");                              // Append type of movie
        switch (type) {
            case ThreeD:
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

    
    public void setDuration(String minutes) {
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
    
    public void setType(String option) {
    	if(option.equals("1"))
			type = Type.ThreeD;
    	else if (option.equals("2"))
			type = Type.NORMAL;
    	else if (option.equals("3"))
    		type = Type.BLOCKBUSTER;
    	else
    		System.out.println("Invalid value entered");
    }

    /* Set the start date of the movie */
    public void setStartDate(String startdate) {

    		this.startDate = startdate;
    		findStatus();
    }   	
    
    /* Set the end date of the movie */
    public void setEndDate(String enddate) {
    			this.endDate = enddate;
    			findStatus();
    }
    
    public String getStartDate(){
    	return startDate;
    }
    
    public String getEndDate(){
    	return endDate;
    }
    
    
    /* Returns new movie abstract */
    public String getMovieAbstract() {
    	return this.movieAbstract;
    }
    /* Allows new movie abstract */
    
    public void setMovieAbstract(String movieabstract) {
    	this.movieAbstract = movieabstract;
    }

    /**
     * Add movieListing for movie
     *
     * @param cineplex At Cineplex
     * @param cinema   In Cinema
     * @param showing  Showing date
     * @see MovieListing
     */
    public void addMovieListing(Cineplex cineplex, Cinema cinema, String showing) {
        this.movielistings.add(new MovieListing(this, cineplex, cinema, showing));
    }

    /**
     * Show all movieListings in stdout
     */
    public void showMovieListings() {
        for (int i = 0; i < movielistings.size(); i++) {
            System.out.println((i + 1) + ": " + movielistings.get(i));
        }
        String now = getToDaysDateString();
        int option = 1;
        
        for (int q = 0; q < movielistings.size(); q++) {
            if (now.compareTo(movielistings.get(q).getShowing()) < 0)
                System.out.println((option++) + ": " + movielistings.get(q));
        }
    }

    private static String getToDaysDateString() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(((month < 10) ? "0" : "") + month);
        sb.append("-");
        sb.append(((day < 10) ? "0" : "") + day);
        sb.append(" | ");
        sb.append(((hour < 10) ? "0" : "") + hour);
        sb.append(":");
        sb.append(((minutes < 10) ? "0" : "") + hour);
        System.out.println("DEBUG: " + sb.toString());
        return sb.toString();
    }

    /**
     * Get movieListing from index
     *
     * @param index Index for movieListing
     * @return Movie Listings at index
     * @see MovieListing
     */
    public MovieListing getMovieListing(int index) {
        return movielistings.get(index);
    }
    
    public ArrayList<MovieListing> getAllMovieListing()
    {
    	return movielistings;
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

