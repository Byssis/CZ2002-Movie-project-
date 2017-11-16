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
	 * @param start startDate of movie
	 * @param end endDate of movie
	 * @return the day difference between the startDate and endDate
	 */
	public static long getDayCount(String start, String end) {
	  long diff = -1;
	  try {
	    Date dateStart = simpleDateFormat.parse(start);
	    Date dateEnd = simpleDateFormat.parse(end);

	    
	    diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
	  } catch (Exception e) {
	   
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
    /*
    	Movie classification like PG/NC16/M18
     */
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
     * @param movieAbstract Short write up/description about the movie
     * @para movieclassification PG/NC16/M18/R21
     */
    public Movie(String title, String director, String duration, ArrayList<String> cast, Type type, String startDate, String endDate, String movieAbstract,String classification) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.type = type;
        this.startDate = startDate;                             
        this.endDate = endDate;                                 
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
    
    /**
     * @param c		movie classification
     * To pass in a string and add it as the movie classification
     */
    public void setMovieClass(String c){
    	this.movieclassification = c;
    }
    
    /**
     * Using dateformatter class to compare the startDate and endDate and get the movie status
     * 4 different status depending on the day difference
     */
    public void findStatus(){
    	Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormatter.format(now);
		if(date.compareTo(startDate)>=0 && date.compareTo(endDate)<=0){
			this.status = MovieStatus.NOW_SHOWING;
		}else if(date.compareTo(endDate)>0){
			this.status = MovieStatus.END_OF_SHOWING;
		}else if(getDayCount(date,startDate)>7){
			this.status = MovieStatus.COMING_SOON;
		}else{
			this.status = MovieStatus.PREVIEW;
		}	
    }
    
    /**
     * @param option    option to set the movie status
     * Function used by admin to overwrite the findStatus function and decide the status of the movie
     */
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

    /**
     *To return the current movie status
     */
    public MovieStatus getStatus(){
    	return this.status;
    }

    /**
     * String representation for all the movie details
     *
     * @return String representation for the movie
     */
    public String toString() {
        StringBuilder out = new StringBuilder();                // Stringbuilder to build out string
        System.out.println();
        out.append("Title:\t\t" + this.title + "\n");          // Append tittle
        out.append("Rating:\t\t" + this.averageRatingStr() + "\n");
        out.append("Director:\t" + this.director + "\n");       // Append Director
        out.append("Abstract: \t" + this.movieAbstract + "\n" );
        out.append("Duration:\t" + this.duration + "\n");  // Append Duration of movie
        out.append("Movie Classification : \t" + this.movieclassification + "\n");
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
     * Print movie details
     */
    public void displayMovieInfo() {
        System.out.println(this.toString());
    }

    /**  
     * @param rating Rating of movie
     * @see Rating
     * Add a new rating to movie
     */
    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    /**  
     * @param review Review of the movie
     * @see Review
     * Add a new review to the movie
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    

    /**  
     * Show all of the reviews to the movie
    */
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
    
    /**  
     * @param newtitle new title for the movie 
     * Set a new title to the movie
     */
    public void setTitle(String newtitle) {
        this.title = newtitle;
    }
    
    /**  
     * @param directorname new director name
     * Set a new director name to the movie
     */

    public void setDirectorname(String directorname) {
        director = directorname;
    }
    
    /**  
     * @param minutes new duration for the movie in minutes
     * Set a new duration in minutes for the movie
     */
    public void setDuration(String minutes) {
    	duration = minutes;
    }
    /**  
     * @param casts new cast name is passed in for update
     * Set the cast name to the new cast name for the movie
     */
    public void setCast(String casts, int k) {
        cast.set(k, casts);
    }

    /**  
     * @param casts new cast name is added into the cast list
     * Add the cast member to the cast list
     */
    public void addCast(String casts) {
        int i;
        for (i = 0; i < cast.size(); i++) {
            cast.add(casts);
        }
    }

    /**  
     * @param casts selected cast name is deleted from the cast list
     * Remove the cast member from the cast list
     */
    public void removeCast(String casts) {
        if (cast.contains(casts))
            cast.remove(casts);
        else
            System.out.println("Cast entered is invalid");
    }

    /**  
     *
     * Get the list of cast members
     * @return ArrayList<String> arraylist of cast members
     */
    public ArrayList<String> getCast() {
        return this.cast;
    }

    /**  
     * @return Type movie type 
     * Return the type of the movie
     */
    public Type getType() {
        return this.type;
    }
    /**  
     * @param option set movie type
     * Set the movie type 
     */
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

    /**  
     * @param startdate startdate of the movie
     * Set the start date of the movie 
     */
    public void setStartDate(String startdate) {

    		this.startDate = startdate;
    		findStatus();
    }   	
    
    /**  
     * @param endDate endDate of the movie
     * Set the end date of the movie 
     */
    public void setEndDate(String enddate) {
    			this.endDate = enddate;
    			findStatus();
    }
    
    /**
     * get start date of movie
     * @return startDate
     */
    public String getStartDate(){
    	return startDate;
    }
    
    /**
     * get end date of movie
     * @return endDate
     */
    public String getEndDate(){
    	return endDate;
    }
    
    
    /**  
     * @return this.movieAbstract abstract of the movie
     * Get the movie abstract of the movie
     */
    public String getMovieAbstract() {
    	return this.movieAbstract;
    }
    /**  
     * @param movieabstract new movie abstract of the movie
     * Set the new movie abstract
     */
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
     * @see Cineplex
     * @see Cinema
     */
    public void addMovieListing(Cineplex cineplex, Cinema cinema, String showing) {
        this.movielistings.add(new MovieListing(this, cineplex, cinema, showing));
    }

    /**  User function
     * @return true/false true if there is movielisting and false if there isn't any movielisting
     * Print movielisting of the movie if it exists and only for movies that are now showing
     */
    public boolean showMovieListings() {
        String now = getTodaysDateString();
        int option = 1;
        for (int q = 0; q < movielistings.size(); q++) {
            if (now.compareTo(movielistings.get(q).getShowing()) < 0)
                System.out.println((option++) + ": " + movielistings.get(q));
        }
        return (option == 1) ? true : false;
    }
    
    /**  
     * Admin function
     * Print movielisting of the movie if it exists even if movies are 
     */
    public void showAdminMovieListings() {
        for (int q = 0; q < movielistings.size(); q++) {
                System.out.println((q+1) + ": " + movielistings.get(q));
        }
    }
    
    /**  
     * To split the String of showing time into its year,month and etc...
     */
    private static String getTodaysDateString() {
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
        return sb.toString();
    }

    /**
     * Get movieListing from index for user to use
     * Only display available for booking ones (Preview and Now Showing)
     * @param index Index for movieListing
     * @return Movie Listings at index
     * @see MovieListing
     */
    public MovieListing getMovieListing(int index) {
        String now = getTodaysDateString();
        int option = 1;
        int q = 0;
        for (; q < movielistings.size(); q++) {
            if (now.compareTo(movielistings.get(q).getShowing()) < 0 && option++ == index)
                break;
        }
        return movielistings.get(q);
    }
    
    /**
     * Get movieListing from index for admin to use
     * Print movielisting even though it is outdated
     * @param index Index for movieListing
     * @return Movie Listings at index
     * @see MovieListing
     */
    public MovieListing getAdminMovieListing(int index) {
       return movielistings.get(index);
    }
    /**
     * Get Array List of movielisting for movie
     * @return arrayList of movieListings
     * @see MovieListing
     */
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
     * @return Number of ticket sales
     */
    public int getTicketSales() {
        return this.ticketSales;
    }

	/**
	 * check if current date is weekend
	 * @return true if weekend, else false
	 */
    public static boolean isValidDate(String inDate) {                        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    
    /**
     * Calculate the average rating of the movie
     * @return Rating of the movie
     */
    public String averageRatingStr() {
        if(this.ratings.size() == 0)
            return "NA";
        return "" + Math.round(this.averageRating()*100)/100;
    }

    /**
     * Get the status of the movie
     * @return Status return the status of the movie
     */
    public String getStatusString() {
        MovieStatus status = this.getStatus();
        if(status == MovieStatus.COMING_SOON)
            return "Coming Soon";
        if(status == MovieStatus.NOW_SHOWING)
            return "Now showing";
        if(status == MovieStatus.PREVIEW)
            return "Preview";
        if(status == MovieStatus.END_OF_SHOWING)
            return "End of showing";

        return "";
    }
}

