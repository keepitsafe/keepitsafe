package info.swegroup.passwordstore;

import info.swegroup.passwordstore.bean.UserBean;
import info.swegroup.passwordstore.bean.UserBeanImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public UserBean getUserBean() {
		return new UserBeanImpl();
	}

}
