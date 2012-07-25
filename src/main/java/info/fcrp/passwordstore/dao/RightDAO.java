package info.fcrp.passwordstore.dao;

import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.Right;
import info.fcrp.passwordstore.model.User;

public interface RightDAO extends GenericDAO<Right> {

	Right findByUserAndPStore(User usr, PasswordStore ps);

}
