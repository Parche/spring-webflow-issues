package es.project.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sun.faces.config.ConfigureListener;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

		@Override
		protected Class<?>[] getRootConfigClasses() {
			return new Class<?>[] { SecurityConfig.class, AppConfig.class };
		}

		@Override
		protected Class<?>[] getServletConfigClasses() {
			return null;
		}

		@Override
		protected String[] getServletMappings() {
			return new String[] { "/spring/*" };
		}

		@Override
		protected Filter[] getServletFilters() {
			return new Filter[] {new CharacterEncodingFilter()
					,new HiddenHttpMethodFilter(),new ShallowEtagHeaderFilter()};
			
			//FileUploadFilter(Primefaces) add as AnnotatedPrimeFacesFileUploadFilter.class
		}
		
		@Override
		protected String getServletName() {
			return "springDispatcher";
		}

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {

			// Use JSF view templates saved as *.xhtml, for use with Facelets
			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			// Enable special Facelets debug output during development
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
			// Causes Facelets to refresh templates during development
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
			// Declare Spring Security Facelets tag library
			servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
			//Save state ui tree on server
			servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD","server");
			//Enable bean validation
			servletContext.setInitParameter("javax.faces.validator.DISABLE_BEAN_VALIDATOR","false");
			//Changing JSF separator
			servletContext.setInitParameter("javax.faces.SEPARATOR_CHAR","-");
			//Validate empty fields
			servletContext.setInitParameter("javax.faces.VALIDATE_EMPTY_FIELDS","false");
			
			servletContext.addListener(ConfigureListener.class);
			servletContext.addListener(RequestContextListener.class);
			servletContext.addListener(HttpSessionEventPublisher.class);

			// Let the DispatcherServlet be registered
			super.onStartup(servletContext);
		}

}
