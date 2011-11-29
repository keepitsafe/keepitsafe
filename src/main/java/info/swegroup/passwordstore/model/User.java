package info.swegroup.passwordstore.model;

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
	
	@Column
	private String password;

	@Column
	private String publicKey;

	@OneToMany(mappedBy = "user")
	private List<Right> rights;

	public User() {
		super();
	}

	public User(String login) {
		this();
		this.login = login;
	}

	public User(String login, String password) {
		this(login);
		this.password = password;
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

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey
	 *            the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
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
