package es.project.servlet;

import javax.servlet.annotation.WebFilter;

import org.primefaces.webapp.filter.FileUploadFilter;

@WebFilter(servletNames={"springDispatcher"})
public class AnnotatedPrimeFacesFileUploadFilter extends FileUploadFilter {
    // NOOP.
}
