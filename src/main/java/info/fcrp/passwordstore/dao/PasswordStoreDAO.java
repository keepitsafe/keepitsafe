package info.fcrp.passwordstore.dao;

import info.fcrp.passwordstore.model.PasswordStore;

public interface PasswordStoreDAO extends GenericDAO<PasswordStore> {

	/**
	 * Get password store by name
	 */
	PasswordStore findByName(String name);

}
