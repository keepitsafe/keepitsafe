package info.fcrp.keepitsafe.dao;

import java.util.List;

import info.fcrp.keepitsafe.model.Keep;

public class KeepDAOImpl extends GenericDAOImpl<Keep>
		implements KeepDAO {

	public Keep findByName(String name) {
		@SuppressWarnings("unchecked")
		List<Keep> list = getHibernateTemplate().find(
				"select ps from PasswordStore ps where ps.name = ?", name);

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
