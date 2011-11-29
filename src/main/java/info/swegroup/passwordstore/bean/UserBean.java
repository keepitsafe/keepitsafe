package info.swegroup.passwordstore.bean;

import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.RightEnum;
import info.swegroup.passwordstore.model.User;

public interface UserBean {
	
	User create(User usr);

	User get(String string);

	void addRight(User usr, PasswordStore ps, String key, RightEnum... rights);

	
}
