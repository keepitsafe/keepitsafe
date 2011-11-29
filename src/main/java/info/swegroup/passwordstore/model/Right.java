package info.swegroup.passwordstore.model;

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
	@ManyToOne
	private User user;

	@ManyToOne
	private PasswordStore passwordStore;

	@Column
	private boolean writer;

	@Column
	private boolean manager;

	@Column
	private boolean admin;

	@Column
	private String passwordStoreKey;

	public Right() {
		super();
	}

	public Right(User usr, PasswordStore ps) {
		this();
		this.user = usr;
		this.passwordStore = ps;
	}

	/**
	 * @return the writer
	 */
	public boolean isWriter() {
		return writer;
	}

	/**
	 * @param writer
	 *            the writer to set
	 */
	public void setWriter(boolean writer) {
		this.writer = writer;
	}

	/**
	 * @return the manager
	 */
	public boolean isManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
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

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the passwordStoreKey
	 */
	public String getPasswordStoreKey() {
		return passwordStoreKey;
	}

	/**
	 * @param passwordStoreKey
	 *            the passwordStoreKey to set
	 */
	public void setPasswordStoreKey(String passwordStoreKey) {
		this.passwordStoreKey = passwordStoreKey;
	}

}
