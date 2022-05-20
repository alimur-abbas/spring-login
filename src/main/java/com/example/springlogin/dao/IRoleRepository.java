package com.example.springlogin.dao;

import com.example.springlogin.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Roles,Long> {
    Roles findByRole(String role);
}
