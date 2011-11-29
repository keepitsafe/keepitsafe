package info.swegroup.passwordstore.bean;

import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.User;

import java.util.List;

public interface PasswordStoreBean {

	/**
	 * Create a new password store
	 */
	PasswordStore create(PasswordStore ps);

	/**
	 * Get a password store
	 */
	PasswordStore get(String string);
}
