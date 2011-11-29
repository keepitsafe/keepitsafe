package info.swegroup.passwordstore.dao;

import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.Right;
import info.swegroup.passwordstore.model.User;

public interface RightDAO extends GenericDAO<Right> {

	Right findByUserAndPStore(User usr, PasswordStore ps);

}
