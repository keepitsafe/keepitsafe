package info.fcrp.passwordstore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The right a user has to access a password
 * 
 * @author felipecrp
 * 
 */
@Entity
public class Right extends ModelObject {
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	private User user;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	private PasswordStore passwordStore;

	public Right() {
		super();
	}

	public Right(User usr, PasswordStore ps) {
		this();
		this.user = usr;
		this.passwordStore = ps;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
