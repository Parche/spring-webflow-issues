package es.project.utils;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageLocale implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger=LogManager.getLogger(LanguageLocale.class);
	
	private String codeLocale;
	
	//<h:commandLink action="#{languageBean.switchLanguage('EN')}">English</h:commandLink>
	public String changeLocale(String code){
		logger.entry("LanguageLocale.changeLocale");
		
		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(new Locale(code));
		
		return FacesContext.getCurrentInstance()
				.getViewRoot().getViewId() + "?faces-redirect=true";
	}

	/**
	 * @return the codeLocale
	 */
	public String getCodeLocale() {
		return codeLocale;
	}

	/**
	 * @param codeLocale the codeLocale to set
	 */
	public void setCodeLocale(String codeLocale) {
		this.codeLocale = codeLocale;
	}
	
	

}
