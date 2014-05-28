package es.project.viewBean;

import java.io.Serializable;
import java.util.Formatter;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger=LogManager.getLogger(Login.class);
	
	@NotEmpty(message="{field.notEmpty.validation}")
	@Email(message="{field.email.validation}")
	private String email;
	
	@NotEmpty(message="{field.notEmpty.validation}")
	@Size(min=6,max=12,message="{field.size.validation}")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message="{field.onlyAlpha.validation}")
	private String password;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
