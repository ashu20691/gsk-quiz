package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String roleName);
}
