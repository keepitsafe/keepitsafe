package info.fcrp.keepitsafe.dao;

import info.fcrp.keepitsafe.model.Secret;

import java.util.List;

public class SecretDAOImpl extends GenericDAOImpl<Secret> implements SecretDAO {

	public List<Secret> findByKeepId(long id) {
		@SuppressWarnings("unchecked")
		List<Secret> list = getHibernateTemplate().findByNamedQueryAndNamedParam("secret.find.keepId", "keepId", id);
		return list;
	}

}
