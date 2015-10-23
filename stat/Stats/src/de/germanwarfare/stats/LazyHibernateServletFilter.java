package de.germanwarfare.stats;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LazyHibernateServletFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("Stats");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			// Create and set the entity manager
			LazyHibernateEntityManagerProvider.setCurrentEntityManager(entityManagerFactory.createEntityManager());

			// Handle the request
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			// Reset the entity manager
			LazyHibernateEntityManagerProvider.setCurrentEntityManager(null);
		}
	}

	@Override
	public void destroy() {
		entityManagerFactory = null;
	}
}
