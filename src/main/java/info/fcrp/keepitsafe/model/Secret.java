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
public class Secret extends ModelObject {
	@Column
	private String name;

	@Column
	private String user;

	@Column
	private String password;

	@ManyToOne
	private Keep keep;

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
	 * @return the value
	 */

	/**
	 * @return the keep
	 */
	public Keep getKeep() {
		return keep;
	}

	/**
	 * @param keep
	 *            the keep to set
	 */
	public void setKeep(Keep keep) {
		this.keep = keep;
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

}
