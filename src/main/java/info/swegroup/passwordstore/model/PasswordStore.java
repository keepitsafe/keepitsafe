package info.swegroup.passwordstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * A set of passwords
 * 
 * @author felipecrp
 * 
 */
@Entity
public class PasswordStore extends ModelObject {
	@Column
	private String name;

	@OneToMany(mappedBy = "passwordStore")
	private List<Right> rights;

	@OneToMany(mappedBy = "passwordStore")
	private List<Password> passwords;
	
	public PasswordStore() {
		super();
	}
	
	public PasswordStore(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rights
	 */
	public List<Right> getRights() {
		return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	/**
	 * @return the passwords
	 */
	public List<Password> getPasswords() {
		return passwords;
	}

	/**
	 * @param passwords the passwords to set
	 */
	public void setPasswords(List<Password> passwords) {
		this.passwords = passwords;
	}

	
}
