package com.example.springrestapi.repos;

import com.example.springrestapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
