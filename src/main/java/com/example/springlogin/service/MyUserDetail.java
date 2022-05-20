package com.example.springlogin.service;


import com.example.springlogin.model.Roles;
import com.example.springlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUserDetail  implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return  new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),user.getActive()
                ,true,true,true,
                authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Roles> roles) {
        Set<GrantedAuthority> rolesDetail = new HashSet<>();
        for (Roles role : roles) {
            rolesDetail.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(rolesDetail);
        return grantedAuthorities;
    }
}
