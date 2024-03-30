package com.example.springrestapi.repos;

import com.example.springrestapi.domain.Role;
import com.example.springrestapi.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Authority> {
}
