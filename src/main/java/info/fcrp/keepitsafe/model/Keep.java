package info.fcrp.keepitsafe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.codehaus.jackson.map.annotate.JsonSerialize;
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

	@Column
	private String description;

	@OneToMany(mappedBy = "keep", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
