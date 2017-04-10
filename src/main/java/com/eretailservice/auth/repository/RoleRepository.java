package com.eretailservice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eretailservice.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
