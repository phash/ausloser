package de.germanwarfare.stats;

import javax.persistence.EntityManager;

public class LazyHibernateEntityManagerProvider {
	private static ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<EntityManager>();

	public EntityManager getEntityManager() {
		return entityManagerThreadLocal.get();
	}

	public static void setCurrentEntityManager(EntityManager em) {
		entityManagerThreadLocal.set(em);
	}
}