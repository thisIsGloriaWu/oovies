package oovies.tools;

import java.sql.SQLException;
<<<<<<< HEAD
import java.text.ParseException;
=======
import java.util.Date;
>>>>>>> origin/pristu
import java.util.List;

import oovies.dal.*;
import oovies.model.*;
import oovies.model.Actor.Gender;

<<<<<<< HEAD
/**
 * Lists of methods:
 * Person:
 *    public Person create(Person person)
 *    public Person delete(Person person)
 *    public Person getPersonByUserName(String userName)
 *    public Person getPersonByUserId(int userId)
 *    public Person updatePassword(Person person, String password)
 *    public Person updateEmail(Person person, String email)
 * Director:
 *     public Director create(Director director)
 *     public Director delete(Director director)
 *     public Director getDirectorByDirectorId(int directorId)
 *     public List<Director> getDirectorByDirectorName(String name)
 *
 *
 *
 */

/**
 * main() runner, used for the app demo.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException, ParseException {
		personDirectorInserter();
		actorStudioInserter();
=======
public class inserter {

	public static void main(String[] args) throws SQLException {
		//DAO instance
		MovieDao movieDao = MovieDao.getInstance();
		CastDao castDao = CastDao.getInstance();
		
		//Movie
		//create movies
		Date date = new Date();
		Movie movie1 = new Movie ("Mulan",date, 4.0, 300, "Mulan is a 2020 American fantasy action drama film produced by Walt Disney Pictures", director1, studio1, Movie.Genre.ACTION);
		movie1 = movieDao.create(movie1);
		Movie movie2 = new Movie ("Call me by your name",date, 5.0, 300, "romance blossoms between a seventeen-year-old student and the older man", director2, studio2, Movie.Genre.DRAMA);
		movie2 = movieDao.create(movie2);
		Movie movie3 = new Movie ("Die hard",date, 1.0, 132, "a 1988 American action film directed by John McTiernan", director3, studio1, Movie.Genre.ACTION);
		movie3 = movieDao.create(movie3);
		
		
		//get movie by movieId
		Movie movieOne = movieDao.getMovieById(1);
		System.out.format("Reading movieOne: movieId:%d title:%s releaseDate:%tx rating:%f durating:%d summary:%s directorId:%d studioId:%d genre:%s \n", 
				movieOne.getMovieId(), movieOne.getTitle(), movieOne.getReleaseDate(), movieOne.getRating(), movieOne.getDuration(), movieOne.getSummary(), 
				movieOne.getDirector().getDirectorId(),
				movieOne.getStudio().gerStudioId(),
				movieOne.getGenre().name());
		
		
		
		// Get movie list by given directorId
		List<Movie> movieDirectorList = movieDao.getMovieByDirectorId(1);
		for (Movie m : movieDirectorList) {
					System.out.format("Looping movies directed by DirectorId 1: movieId:%d title:%s directorId:%d studioId:%d\n", 
					m.getMovieId(), m.getTitle(),
					movieOne.getDirector().getDirectorId(),
					movieOne.getStudio().gerStudioId());
		}
				
				
		// Get movie list by given studioId
		List<Movie> movieStudioList = movieDao.getMovieByStudioId(1);
		for (Movie m : movieStudioList) {
					System.out.format("Looping movies by StudioId 1: movieId:%d title:%s directorId:%d studioId:%d\n", 
					m.getMovieId(), m.getTitle(),
					movieOne.getDirector().getDirectorId(),
					movieOne.getStudio().gerStudioId());
		}
				
		// Delete movie
		System.out.println("Deleting movie2 ...");
		Movie movie2Deleted = movieDao.delete(movie2);
		if (movie2Deleted == null) {
			System.out.println("movie2 has been successfully deleted.");
		}
		
		//Update
		System.out.format("Before updadte movie summary, summary:%s\n", movie1.getSummary());
		Movie movie4 = movieDao.updateSummary(movie1, "nothing to tell");
		System.out.format("After updadte movie summary, summary:%s\n", movie4.getSummary());

		
		//Cast
		//Create cast
		Cast cast1 = new Cast(actor1, movie1);
		Cast cast2 = new Cast(actor1, movie2);
		Cast cast3 = new Cast(actor1, movie3);
		Cast cast4 = new Cast(actor2, movie1);
		
		
		//get cast by castId
		Cast castOne = castDao.getCastById(1);
		System.out.format("Reading castOne: castId:%d movieTitle:%s, actorName:%s \n",
				castOne.getCastId(), castOne.getMovie().getTitle(), castOne.getActor().getName());
		
		// Get cast list by given movieId
		List<Cast> castMovieList = castDao.getCastByMovieId(1);
		for (Cast c : castMovieList) {
			System.out.format("Looping cast by MovieId 1: castId:%d title:%s actorName:%s\n", 
					c.getCastId(), c.getMovie().getTitle(),c.getActor().getName());
		}
		
		// Get cast list by given movieId
		List<Cast> castActorList = castDao.getCastByActorId(1);
		for (Cast c : castActorList) {
			System.out.format("Looping cast by ActorId 1: castId:%d title:%s actorName:%s\n", 
				c.getCastId(), c.getMovie().getTitle(),c.getActor().getName());
		}
		
		// Delete cast
		System.out.println("Deleting cast2 ...");
		Cast cast2Deleted = castDao.delete(cast2);
		if (cast2Deleted == null) {
			System.out.println("cast2 has been successfully deleted.");
		}
		
>>>>>>> origin/pristu
	}
	
	public static void personDirectorInserter() throws SQLException {
		PersonDao personDao = PersonDao.getInstance();
		DirectorDao directorDao = DirectorDao.getInstance();

		// INSERT objects from our model.
		Person person = new Person("user", "password", "email", Person.Role.ADMIN);
		Person person1 = new Person("user1", "password1", "email1", Person.Role.USER);
		personDao.create(person);
		personDao.create(person1);
		Director director = new Director("director", Director.Gender.F);
		Director director1 = new Director("director1", Director.Gender.M);
		Director director2 = new Director("director", Director.Gender.F);
		directorDao.create(director);
		directorDao.create(director1);
		directorDao.create(director2);

		// READ.
		Person person2 = personDao.getPersonByUserName("user");
		System.out.format("Reading person by userName: id:%d name:%s password:%s email:%s role:%s \n",
				person2.getUserId(), person2.getUserName(), person2.getPassword(), person2.getEmail(), person2.getRole().name());
		Person person3 = personDao.getPersonByUserId(person2.getUserId());
		System.out.format("Reading person by userId: id:%d name:%s password:%s email:%s role:%s \n",
				person3.getUserId(), person3.getUserName(), person3.getPassword(), person3.getEmail(), person3.getRole().name());
		List<Director> dList = directorDao.getDirectorByDirectorName("director");
		int directorId = -1;
		for(Director d : dList){
			directorId = d.getDirectorId();
			System.out.format("Looping directors by directorName: id:%d name:%s gender:%s \n",
					d.getDirectorId(), d.getName(), d.getGender().name());

		}
		Director director3 = directorDao.getDirectorByDirectorId(directorId);
		System.out.format("Reading director by directorId: id:%d name:%s gender:%s \n",
				director3.getDirectorId(), director3.getName(), director3.getGender().name());

		// UPDATE.
		System.out.format("Reading person before updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person2.getUserId(), person2.getUserName(), person2.getPassword(), person2.getEmail(), person2.getRole().name());
		Person person31 = personDao.updatePassword(person2, "newPassword");
		System.out.format("Reading person after updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person31.getUserId(), person31.getUserName(), person31.getPassword(), person31.getEmail(), person31.getRole().name());
		Person person4 = personDao.updateEmail(person31, "newEmail");
		System.out.format("Reading person after updating email: id:%d name:%s password:%s email:%s role:%s \n",
				person4.getUserId(), person4.getUserName(), person4.getPassword(), person4.getEmail(), person4.getRole().name());

		// DELETE.

		personDao.delete(person1);
		directorDao.delete(director2);

	}


	public static void actorStudioInserter() throws SQLException {
		// DAO instances
		ActorDao actorDao = ActorDao.getInstance();
		StudioDao studioDao = StudioDao.getInstance();

		//INSERT
		Actor actor = new Actor("bruce", Actor.Gender.M);
		actor = actorDao.create(actor);
		Actor actor2 = new Actor("bruce", Actor.Gender.M);
		actor2 = actorDao.create(actor2);

		Studio studio = new Studio("studio1","location1");
		studio = studioDao.create(studio);
		Studio studio2 = new Studio("studio1","location1");
		studio2 = studioDao.create(studio2);

		//READ
		List<Actor> actorList1 = actorDao.getActorByName("bruce");
		for(Actor a : actorList1) {
			System.out.format("Looping actor: id:%s name:%s gender:%s \n",
				a.getActorId(), a.getName(), a.getGender().name());
		}
		
		List<Studio> studioList1 = studioDao.getStudioFromName("studio1");
		for(Studio a : studioList1){
			System.out.format("Looping studio: id:%s name:%s location:%s \n",
				a.getStudioId(), a.getName(), a.getLocation());
		}

		List<Studio> studioList2 = studioDao.getStudioFromLocation("location1");
		for(Studio a : studioList2){
			System.out.format("Looping studio: id:%s name:%s location:%s \n",
				a.getStudioId(), a.getName(), a.getLocation());
		}
		
		//UPDATE
		System.out.format("Before updadte actor name, name:%s\n", actor.getName());
		Actor actor3 = actorDao.updateName(actor, "newbruce");
		System.out.format("After updadte actor name, name:%s\n", actor3.getName());

		System.out.format("Before updadte studio name, name:%s\n", studio.getName());
		Studio studio3 = studioDao.updateName(studio, "newstudio");
		System.out.format("After updadte studio name, name:%s\n", studio3.getName());
		
		System.out.format("Before updadte studio location, location:%s\n", studio.getLocation());
		Studio studio4 = studioDao.updateName(studio, "newlocation");
		System.out.format("After updadte studio name, name:%s\n", studio3.getLocation());
		
		//DELETE
		actorDao.delete(actor2);
		studioDao.delete(studio2);
	}

}
