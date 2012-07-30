package info.fcrp.keepitsafe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * A password. Nothing else ;)
 * 
 * @author felipecrp
 * 
 */
@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@NamedQueries({
		@NamedQuery(name="secret.find.keepId", query="select sc from Secret sc where sc.keep.id = :keepId")
})
public class Secret extends ModelObject {
	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String login;
	
	@Column
	private String password;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
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

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
