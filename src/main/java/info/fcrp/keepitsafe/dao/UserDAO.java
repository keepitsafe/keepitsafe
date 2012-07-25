package info.fcrp.keepitsafe.dao;

import info.fcrp.keepitsafe.model.User;

public interface UserDAO extends GenericDAO<User> {

	User findByLogin(String login);

}
