package info.fcrp.keepitsafe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Service;

/**
 * A set of passwords
 * 
 * @author felipecrp
 * 
 */
@Entity
public class Keep extends ModelObject {
	@Column
	private String name;

	@OneToMany(mappedBy = "keep")
	private List<Right> rights;

	@OneToMany(mappedBy = "keep")
	private List<Secret> secrets;

	public Keep() {
		super();
	}

	public Keep(String name) {
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
	 * @param name
	 *            the name to set
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
	 * @param rights
	 *            the rights to set
	 */
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	/**
	 * @return the secrets
	 */
	public List<Secret> getSecrets() {
		return secrets;
	}

	/**
	 * @param secrets
	 *            the secrets to set
	 */
	public void setSecrets(List<Secret> secrets) {
		this.secrets = secrets;
	}

}
