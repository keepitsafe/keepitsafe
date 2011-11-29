package info.swegroup.passwordstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * A password. Nothing else ;)
 * 
 * @author felipecrp
 * 
 */
@Entity
public class Password extends ModelObject {
	@Column
	private String name;
	
	@Column
	private String user;
	
	@Column
	private String password;
	
	@ManyToOne
	private PasswordStore passwordStore;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordStore
	 */
	public PasswordStore getPasswordStore() {
		return passwordStore;
	}

	/**
	 * @param passwordStore
	 *            the passwordStore to set
	 */
	public void setPasswordStore(PasswordStore passwordStore) {
		this.passwordStore = passwordStore;
	}

}
