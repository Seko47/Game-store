package com.example.projekt.repositories;

import com.example.projekt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository< Role, Integer >
{
    Role findRoleByType ( Role.Types roleType );
}
