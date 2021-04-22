package com.example.clothingstoreprojectteam.repository;

import com.example.clothingstoreprojectteam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
}
