package info.fcrp.keepitsafe.model;

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
	private String value;

	@ManyToOne
	private Keep passwordStore;

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
	 * @return the passwordStore
	 */
	public Keep getPasswordStore() {
		return passwordStore;
	}

	/**
	 * @param passwordStore
	 *            the passwordStore to set
	 */
	public void setPasswordStore(Keep passwordStore) {
		this.passwordStore = passwordStore;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
