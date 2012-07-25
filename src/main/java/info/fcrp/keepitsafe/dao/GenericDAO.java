package info.fcrp.keepitsafe.dao;

import java.util.List;

import info.fcrp.keepitsafe.model.ModelObject;

public interface GenericDAO<OBJ extends ModelObject> {

	void save(OBJ object);

	void delete(OBJ object);

	OBJ find(int id);

	List<OBJ> findAll();
}
