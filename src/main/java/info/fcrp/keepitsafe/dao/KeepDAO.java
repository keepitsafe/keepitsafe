package info.fcrp.keepitsafe.dao;

import info.fcrp.keepitsafe.model.Keep;

public interface KeepDAO extends GenericDAO<Keep> {

	/**
	 * Get password store by name
	 */
	Keep findByName(String name);

}
