package com.example.springlogin.service;

import com.example.springlogin.dao.IRoleRepository;
import com.example.springlogin.dao.IUserDao;
import com.example.springlogin.model.Roles;
import com.example.springlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    private IUserDao iloginDao;
    private IRoleRepository iRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired

    public UserService(IUserDao iloginDao,IRoleRepository iRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iloginDao = iloginDao;
        this.iRoleRepository = iRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User findUserByEmail(String email) {
        return iloginDao.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return iloginDao.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Roles userRole = iRoleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Roles>(Arrays.asList(userRole)));
        return iloginDao.save(user);
    }
}
