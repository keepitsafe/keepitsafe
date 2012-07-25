package info.fcrp.passwordstore.bean;

import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.User;

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
	void addRight(User usr, PasswordStore ps);
}
