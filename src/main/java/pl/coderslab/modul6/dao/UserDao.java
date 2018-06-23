package pl.coderslab.modul6.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.modul6.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	User findByUserName(String userName);
}
