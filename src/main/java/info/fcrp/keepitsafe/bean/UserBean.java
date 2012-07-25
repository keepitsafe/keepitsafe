package info.fcrp.keepitsafe.bean;

import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.User;

public interface UserBean {
	
	/**
	 * Create a new user
	 * @param login the user login
	 * @return the user
	 */
	User create(String login);

	/**
	 * Get the user
	 * @param login the user login
	 * @return the user
	 */
	User get(String string);

	/**
	 * Allow an user to access a password store
	 * @param usr the user
	 * @param ps the password store
	 */
	void addRight(User usr, Keep ps);
}
