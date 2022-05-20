package com.example.springlogin.dao;

import com.example.springlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUserName(String userName);


}
