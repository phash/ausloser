package de.germanwarfare.stats;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import de.germanwarfare.stats.entity.Level;

public class LevelService {

	private ArrayList<Level> levels;

	public LevelService() {
		super();
		em = Persistence.createEntityManagerFactory("awestat").createEntityManager();
		levels = new ArrayList<>();
	}

	EntityManager em;

	private Level createLevelByName(String string) {
		Level neu = new Level(string);
		levels.add(neu);
		// SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		//
		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// session.save(neu);
		em.persist(neu);
		System.out.println("new level: " + string);
		return neu;
	}

	public Level getLevelByName(String string) {
		for (Level level : levels) {
			if (level.getName().equals(string)) {
				return level;
			}
		}
		return createLevelByName(string);
	}

}
