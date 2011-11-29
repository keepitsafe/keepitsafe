package info.swegroup.passwordstore.dao;

import java.util.List;

import info.swegroup.passwordstore.model.PasswordStore;

public class PasswordStoreDAOImpl extends GenericDAOImpl<PasswordStore>
		implements PasswordStoreDAO {

	public PasswordStore findByName(String name) {
		@SuppressWarnings("unchecked")
		List<PasswordStore> list = getHibernateTemplate().find(
				"select ps from PasswordStore ps where ps.name = ?", name);

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
