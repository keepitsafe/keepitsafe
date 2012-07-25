package info.fcrp.keepitsafe.dao;

import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.Right;
import info.fcrp.keepitsafe.model.User;

public interface RightDAO extends GenericDAO<Right> {

	Right findByUserAndPStore(User usr, Keep ps);

}
