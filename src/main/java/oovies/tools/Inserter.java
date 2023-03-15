package oovies.tools;

import java.sql.SQLException;
import java.text.ParseException;

import oovies.dal.*;
import oovies.model.*;
import oovies.model.Actor.Gender;



/**
 * main() runner, used for the app demo.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException, ParseException {
		actorStudioInserter();
	}


	public static void actorStudioInserter() {
		// DAO instances
		ActorDao actorDao = ActorDao.getInstance();
		StudioDao studioDao = StudioDao.getInstance();

		//INSERT
		Actor actor = new Actor("bruce", Actor.Gender.M);
		actor = actorDao.create(actor);
		Actor actor2 = new Actor("bruce", Actor.Gender.M);
		actor1 = actorDao.create(actor1);

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
		Actor actor3 = actorDao.updateName(actor, "newbruce")
		System.out.format("After updadte actor name, name:%s\n", actor3.getName());

		System.out.format("Before updadte studio name, name:%s\n", studio.getName());
		Studio studio3 = studioDao.updateName(studio, "newstudio")
		System.out.format("After updadte studio name, name:%s\n", studio3.getName());
		
		System.out.format("Before updadte studio location, location:%s\n", studio.getLocation());
		Studio studio4 = studioDao.updateName(studio, "newlocation");
		System.out.format("After updadte studio name, name:%s\n", studio3.getLocation());
		
		//DELETE
		actorDao.delete(actor2);
		studio.delete(studio2);
	}

}
