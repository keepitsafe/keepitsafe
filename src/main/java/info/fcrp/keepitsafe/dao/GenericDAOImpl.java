package info.fcrp.keepitsafe.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import info.fcrp.keepitsafe.model.ModelObject;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericDAOImpl<OBJ extends ModelObject> extends
		HibernateDaoSupport implements GenericDAO<OBJ> {

	private Class<OBJ> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.persistentClass = (Class<OBJ>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// Classe que será persistida.

	public void save(OBJ object) {
		getHibernateTemplate().save(object);
	}

	public void update(OBJ object) {
		getHibernateTemplate().update(object);
	}

	public void delete(OBJ object) {
		getHibernateTemplate().delete(object);
	}

	@SuppressWarnings("unchecked")
	public OBJ find(long id) {
		return (OBJ) getHibernateTemplate().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<OBJ> findAll() {
		return getHibernateTemplate().loadAll(persistentClass);
	}

}
