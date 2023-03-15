package oovies.model;

public class Love {
	protected int loveId;
	protected Person user;
	protected Movie movie;
	
	public Love(int loveId, Person user, Movie movie) {
		super();
		this.loveId = loveId;
		this.user = user;
		this.movie = movie;
	}
	public Love(Person user, Movie movie) {
		super();
		this.user = user;
		this.movie = movie;
	}
	public Love(int loveId) {
		super();
		this.loveId = loveId;
	}
	
	
	
	/** Getters and Setters **/
	
	public int getLoveId() {
		return loveId;
	}
	public void setLoveId(int loveId) {
		this.loveId = loveId;
	}
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
}