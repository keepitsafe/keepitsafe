package info.swegroup.passwordstore.dao;

import info.swegroup.passwordstore.model.PasswordStore;

public interface PasswordStoreDAO extends GenericDAO<PasswordStore> {

	/**
	 * Get password store by name
	 */
	PasswordStore findByName(String name);

}
