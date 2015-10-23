package de.germanwarfare.stats;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.vaadin.data.hbnutil.ApplicationLogger;

public class Hibernator {
	// @WebFilter(urlPatterns = { "/*" }, dispatcherTypes = {
	// DispatcherType.REQUEST, DispatcherType.FORWARD,
	// DispatcherType.INCLUDE, DispatcherType.ERROR })
	public class SessionInterceptor implements Filter {
		private final ApplicationLogger logger = new ApplicationLogger(SessionInterceptor.class);

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			logger.executionTrace();
		}

		@Override
		public void destroy() {
			logger.executionTrace();
			final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
				session.getTransaction().commit();
			}

			if (session.isOpen()) {
				session.close();
			}
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			try {
				if (!session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.beginTransaction();
				}

				chain.doFilter(request, response);

				if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.getTransaction().commit();
				}
			} catch (StaleObjectStateException e) {
				logger.error(e);

				if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.getTransaction().rollback();
				}

				throw e;
			} catch (Throwable e) {
				logger.error(e);

				if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.getTransaction().rollback();
				}

				throw new ServletException(e);
			}
		}
	}
}