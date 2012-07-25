package info.fcrp.passwordstore.dao;

import info.fcrp.passwordstore.model.User;

public interface UserDAO extends GenericDAO<User> {

	User findByLogin(String login);

}
