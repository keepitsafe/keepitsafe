package info.swegroup.passwordstore.dao;

import info.swegroup.passwordstore.model.User;

public interface UserDAO extends GenericDAO<User> {

	User findByLogin(String login);

}
