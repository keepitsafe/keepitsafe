package info.fcrp.passwordstore.dao;

import java.util.List;

import info.fcrp.passwordstore.model.User;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public User findByLogin(String login) {
		@SuppressWarnings("unchecked")
		List<User> usrs = getHibernateTemplate().find(
				"select usr from User usr where usr.login = ?", login);

		if (usrs.size() > 0) {
			return usrs.get(0);

		} else {
			return null;
		}
	}
}
