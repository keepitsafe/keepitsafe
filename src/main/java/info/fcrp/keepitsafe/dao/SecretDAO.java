package info.fcrp.keepitsafe.dao;

import java.util.List;

import info.fcrp.keepitsafe.model.Secret;

public interface SecretDAO extends GenericDAO<Secret> {

	/**
	 * Find all secrets from a keep
	 * @param id the keep id
	 * @return the list of secrets
	 */
	List<Secret> findByKeepId(long id);

}
