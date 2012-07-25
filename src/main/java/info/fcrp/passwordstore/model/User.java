package info.fcrp.passwordstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * The user that has access to a password
 * 
 * @author felipecrp
 * 
 */
@Entity
public class User extends ModelObject {

	@Column
	private String login;

	@OneToMany(mappedBy = "user")
	private List<Right> rights;

	public User() {
		super();
	}

	public User(String login) {
		this();
		this.login = login;
	}

	/**
	 * @return the rights
	 */
	public List<Right> getRights() {
		return rights;
	}

	/**
	 * @param rights
	 *            the rights to set
	 */
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
