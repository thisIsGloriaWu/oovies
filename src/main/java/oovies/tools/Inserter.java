package oovies.tools;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oovies.dal.ActorDao;
import oovies.dal.CastsDao;
import oovies.dal.DirectorDao;
import oovies.dal.FollowDao;
import oovies.dal.LoveDao;
import oovies.dal.MovieDao;
import oovies.dal.PersonDao;
import oovies.dal.StudioDao;
import oovies.model.Actor;
import oovies.model.Casts;
import oovies.model.Director;
import oovies.model.Follow;
import oovies.model.Love;
import oovies.model.Movie;
import oovies.model.Person;
import oovies.model.Studio;

/**
 * Lists of methods: Person: public Person create(Person person) public Person
 * delete(Person person) public Person getPersonByUserName(String userName)
 * public Person getPersonByUserId(int userId) public Person
 * updatePassword(Person person, String password) public Person
 * updateEmail(Person person, String email) Director: public Director
 * create(Director director) public Director delete(Director director) public
 * Director getDirectorByDirectorId(int directorId) public List<Director>
 * getDirectorByDirectorName(String name)
 *
 *
 *
 */

public class Inserter {
	private static PersonDao personDao = PersonDao.getInstance();
	private static DirectorDao directorDao = DirectorDao.getInstance();
	private static ActorDao actorDao = ActorDao.getInstance();
	private static StudioDao studioDao = StudioDao.getInstance();
	private static MovieDao movieDao = MovieDao.getInstance();
	private static CastsDao castDao = CastsDao.getInstance();
	private static FollowDao followDao = FollowDao.getInstance();
	private static LoveDao loveDao = LoveDao.getInstance();

	private static List<Person> persons = new ArrayList<>();
	private static List<Director> directors = new ArrayList<>();
	private static List<Actor> actors = new ArrayList<>();
	private static List<Studio> studios = new ArrayList<>();
	private static List<Movie> movies = new ArrayList<>();
	private static List<Casts> casts = new ArrayList<>();
	private static List<Follow> follows = new ArrayList<>();
	private static List<Love> loves = new ArrayList<>();

	private static Date date = new Date();

	public static void main(String[] args) throws SQLException, ParseException {
		// Initialize test models.
		persons.add(new Person("user", "password", "email", Person.Role.ADMIN));
		persons.add(new Person("user1", "password1", "email1", Person.Role.USER));

		directors.add(new Director("director", Director.Gender.F));
		directors.add(new Director("director1", Director.Gender.M));
		directors.add(new Director("director2", Director.Gender.F));

		actors.add(new Actor("bruce1", Actor.Gender.M));
		actors.add(new Actor("bruce2", Actor.Gender.M));

		studios.add(new Studio("studio1", "location1"));
		studios.add(new Studio("studio2", "location2"));

		movies.add(new Movie("Mulan", date, 4.0, 300,
				"Mulan is a 2020 American fantasy action drama film produced by Walt Disney Pictures", directors.get(0),
				studios.get(0), Movie.Genre.ACTION));
		movies.add(new Movie("Call me by your name", date, 5.0, 300,
				"romance blossoms between a seventeen-year-old student and the older man", directors.get(1),
				studios.get(1), Movie.Genre.DRAMA));
		movies.add(new Movie("Die hard", date, 1.0, 132, "a 1988 American action film directed by John McTiernan",
				directors.get(2), studios.get(1), Movie.Genre.ACTION));

		casts.add(new Casts(movies.get(0), actors.get(0)));
		casts.add(new Casts(movies.get(1), actors.get(0)));
		casts.add(new Casts(movies.get(2), actors.get(0)));
		casts.add(new Casts(movies.get(0), actors.get(1)));

		// Test Create Methods for all classes.
		createAll();

		// Test Read Methods for all classes.
		readAll();

		// Test Update Methods for all classes.
		updateAll();

		// Test Delete Methods for all classes.
		deleteAll();

	}

	public static void createAll() throws SQLException {
		for (Person person : persons) {
			personDao.create(person);
		}

		for (Director director : directors) {
			Director temp = directorDao.create(director);
			System.out.println(temp);
		}

		for (Actor actor : actors) {
			actorDao.create(actor);
		}

		for (Studio studio : studios) {
			studioDao.create(studio);
		}

		for (Movie movie : movies) {
			movieDao.create(movie);
		}

		for (Casts cast : casts) {
			castDao.create(cast);
		}
	}

	public static void readAll() throws SQLException {
		/**
		 * Read methods from Person.
		 */
		Person person = persons.get(0);
		System.out.println(person);
		int personId = person.getUserId();
		String personName = person.getUserName();

		// Get person by given userId
		person = personDao.getPersonByUserId(personId);
		System.out.format("Reading person by userId %d: id:%d name:%s password:%s email:%s role:%s \n", personId,
				person.getUserId(), person.getUserName(), person.getPassword(), person.getEmail(),
				person.getRole().name());

		// Get person by given userName
		person = personDao.getPersonByUserName(personName);
		System.out.format("Reading person by userName %s: id:%d name:%s password:%s email:%s role:%s \n", personName,
				person.getUserId(), person.getUserName(), person.getPassword(), person.getEmail(),
				person.getRole().name());

		
		/**
		 * Read methods from Director.
		 */
		Director director = directors.get(0);
		int directorId = director.getDirectorId();
		String directorName = director.getName();

		// Get director by given directorId
		director = directorDao.getDirectorByDirectorId(directorId);
		System.out.format("Reading director by directorId %d: id:%d name:%s gender:%s \n", directorId,
				director.getDirectorId(), director.getName(), director.getGender().name());

		// Get directors' list by given directorName
		for (Director other : directorDao.getDirectorByDirectorName(directorName)) {
			System.out.format("Reading director by directorName %s: id:%d name:%s gender:%s \n", directorName,
					other.getDirectorId(), other.getName(), other.getGender().name());
		}

		
		/**
		 * Read methods from Actor.
		 */
		Actor actor = actors.get(0);
		String actorName = actor.getName();

		// Get actors' list by given actorName
		for (Actor other : actorDao.getActorByName(actorName)) {
			System.out.format("Reading actor by actorName %s: id:%d name:%s gender:%s \n", actorName,
					other.getActorId(), other.getName(), other.getGender().name());
		}

		
		/**
		 * Read methods from Studio.
		 */
		Studio studio = studios.get(0);
		String studioName = studio.getName();
		String studioLocation = studio.getLocation();

		// Get studios' list by given studioName
		for (Studio other : studioDao.getStudioFromName(studioName)) {
			System.out.format("Reading studio by studioName %s: id:%d name:%s location:%s \n", studioName,
					other.getStudioId(), other.getName(), other.getLocation());
		}

		// Get studios' list by given studioLocation
		for (Studio other : studioDao.getStudioFromLocation(studioLocation)) {
			System.out.format("Reading studio by studioLocation %s: id:%d name:%s location:%s \n", studioLocation,
					other.getStudioId(), other.getName(), other.getLocation());
		}

		
		/**
		 * Read methods from Movie.
		 */
		Movie movie = movies.get(0);
		int movieId = movie.getMovieId();
		int movieDirectorId = movie.getDirector().getDirectorId();
		int movieStudioId = movie.getStudio().getStudioId();

		// Get movie by given movieId
		movie = movieDao.getMovieById(movieId);
		System.out.format(
				"Reading movie by movieId %d: movieId:%d title:%s releaseDate:%s rating:%f durating:%d summary:%s directorId:%d studioId:%d genre:%s \n",
				movieId, movie.getMovieId(), movie.getTitle(), movie.getReleaseDate().toString(), movie.getRating(),
				movie.getDuration(), movie.getSummary(), movie.getDirector().getDirectorId(),
				movie.getStudio().getStudioId(), movie.getGenre().name());

		// Get movies' list by given directorId
		for (Movie other : movieDao.getMovieByDirectorId(movieDirectorId)) {
			System.out.format(
				"Reading movie by directorId %d: movieId:%d title:%s releaseDate:%s rating:%f durating:%d summary:%s directorId:%d studioId:%d genre:%s \n",
				movieDirectorId, other.getMovieId(), other.getTitle(), other.getReleaseDate().toString(), other.getRating(),
				other.getDuration(), other.getSummary(), other.getDirector().getDirectorId(),
				other.getStudio().getStudioId(), other.getGenre().name());
		}
		
		// Get movies' list by given studioId
		for (Movie other : movieDao.getMovieByDirectorId(movieStudioId)) {
			System.out.format(
				"Reading movie by studioId %d: movieId:%d title:%s releaseDate:%s rating:%f durating:%d summary:%s directorId:%d studioId:%d genre:%s \n",
				movieStudioId, other.getMovieId(), other.getTitle(), other.getReleaseDate().toString(), other.getRating(),
				other.getDuration(), other.getSummary(), other.getDirector().getDirectorId(),
				other.getStudio().getStudioId(), other.getGenre().name());
		}
		
		
		/**
		 * Read methods from Cast.
		 */
		Casts cast = casts.get(0);
		int castId = cast.getCastId();
		int castMovieId = cast.getMovie().getMovieId();
		int castActorId = cast.getActor().getActorId();
		
		// Get cast by given castId
		cast = castDao.getCastById(castId);
		System.out.format("Reading cast by castId %d: castId:%d movieTitle:%s, actorName:%s \n", 
				castId, cast.getCastId(), cast.getMovie().getTitle(), cast.getActor().getName());
		
		// Get casts' list by given movieId
		for (Casts other : castDao.getCastByMovieId(castMovieId)) {
			System.out.format("Reading cast by MovieId %d: castId:%d title:%s actorName:%s\n", castMovieId, other.getCastId(),
					other.getMovie().getTitle(), other.getActor().getName());
		}
		
		// Get casts' list by given actorId
		for (Casts other : castDao.getCastByActorId(castActorId)) {
			System.out.format("Reading cast by ActorId %d: castId:%d title:%s actorName:%s\n", castActorId, other.getCastId(),
					other.getMovie().getTitle(), other.getActor().getName());
		}
	}

	public static void updateAll() throws SQLException {
		/**
		 * Update methods from Person
		 */
		Person person = persons.get(0);
		System.out.format("Reading person before updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person.getUserId(), person.getUserName(), person.getPassword(), person.getEmail(),
				person.getRole().name());
		// Update password
		person = personDao.updatePassword(person, "newPassword");
		System.out.format("Reading person after updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person.getUserId(), person.getUserName(), person.getPassword(), person.getEmail(),
				person.getRole().name());
		// Update email
		person = personDao.updateEmail(person, "newEmail");
		System.out.format("Reading person after updating email: id:%d name:%s password:%s email:%s role:%s \n",
				person.getUserId(), person.getUserName(), person.getPassword(), person.getEmail(),
				person.getRole().name());
		
		/**
		 * Update methods from Actor
		 */
		Actor actor = actors.get(0);
		System.out.format("Before update actor name, name:%s\n", actor.getName());
		actor = actorDao.updateName(actor, "newbruce");
		System.out.format("After update actor name, name:%s\n", actor.getName());
		
		
		/**
		 * Update methods from Studio
		 */
		Studio studio = studios.get(0);
		// Update studio name
		System.out.format("Before update studio name, name:%s\n", studio.getName());
		studio = studioDao.updateName(studio, "newstudio");
		System.out.format("After update studio name, name:%s\n", studio.getName());

		// Update studio location
		System.out.format("Before update studio location, location:%s\n", studio.getLocation());
		studio = studioDao.updateLocation(studio, "newlocation");
		System.out.format("After update studio location, location:%s\n", studio.getLocation());
		
		/**
		 * Update methods from Movie
		 */
		Movie movie = movies.get(0);
		System.out.format("Before update movie summary, summary:%s\n", movie.getSummary());
		movie = movieDao.updateSummary(movie, "nothing to tell");
		System.out.format("After update movie summary, summary:%s\n", movie.getSummary());
	}

	public static void deleteAll() throws SQLException {
		for (Person person : persons) {
			personDao.delete(person);
		}

		for (Director director : directors) {
			directorDao.delete(director);
		}

		for (Actor actor : actors) {
			actorDao.delete(actor);
		}

		for (Studio studio : studios) {
			studioDao.delete(studio);
		}

		for (Movie movie : movies) {
			movieDao.delete(movie);
		}

		for (Casts cast : casts) {
			castDao.delete(cast);
		}
	}
}
