package pl.coderslab.modul6.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.coderslab.modul6.dao.UserDao;
import pl.coderslab.modul6.entity.User;

@Service
public class DBUserDertailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles()
				.build();
		return userDetails;
	}
}
