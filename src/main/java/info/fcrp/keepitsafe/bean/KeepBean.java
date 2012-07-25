package info.fcrp.keepitsafe.bean;

import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.User;

import java.util.List;

public interface KeepBean {

	/**
	 * Create a new password store
	 */
	Keep create(String string);

	/**
	 * Get a password store
	 */
	Keep get(String string);
}
