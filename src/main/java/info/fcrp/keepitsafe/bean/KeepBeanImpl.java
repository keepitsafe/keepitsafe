package info.fcrp.keepitsafe.bean;

import info.fcrp.keepitsafe.dao.KeepDAO;
import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.User;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class KeepBeanImpl implements KeepBean {

	@Autowired
	KeepDAO passwordStoreDAO;

	public Keep create(String name) {
		
		Keep ps = new Keep(name);
		passwordStoreDAO.save(ps);
		return ps;
	}

	public Keep get(String name) {
		Keep ps = passwordStoreDAO.findByName(name);
		return ps;
	}
}
