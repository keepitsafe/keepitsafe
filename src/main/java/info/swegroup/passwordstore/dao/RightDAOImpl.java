package info.swegroup.passwordstore.dao;

import java.util.List;

import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.Right;
import info.swegroup.passwordstore.model.User;

public class RightDAOImpl extends GenericDAOImpl<Right> implements RightDAO {

	public Right findByUserAndPStore(User usr, PasswordStore ps) {
		if(usr == null || ps == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<Right> rights = getHibernateTemplate()
				.find("select r from Right r where r.user.id = ? and r.passwordStore.id = ?",
						new Object[] { usr.getId(), ps.getId() });

		if (rights.size() > 0) {
			return rights.get(0);
		} else {
			return null;
		}
	}
}
