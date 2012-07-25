package info.fcrp.keepitsafe.model;

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
	private Keep keep;

	public Right() {
		super();
	}

	public Right(User usr, Keep kp) {
		this();
		this.user = usr;
		this.keep = kp;
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
	 * @return the keep
	 */
	public Keep getKeep() {
		return keep;
	}

	/**
	 * @param keep the keep to set
	 */
	public void setKeep(Keep keep) {
		this.keep = keep;
	}

	
}
