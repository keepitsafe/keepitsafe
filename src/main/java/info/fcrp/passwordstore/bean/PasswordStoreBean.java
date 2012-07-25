package info.fcrp.passwordstore.bean;

import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.User;

import java.util.List;

public interface PasswordStoreBean {

	/**
	 * Create a new password store
	 */
	PasswordStore create(String string);

	/**
	 * Get a password store
	 */
	PasswordStore get(String string);
}
