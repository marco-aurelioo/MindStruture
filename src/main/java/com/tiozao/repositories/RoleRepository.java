package com.tiozao.repositories;

import com.tiozao.entities.RoleEntity;
import com.tiozao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository< RoleEntity,Integer> {
}
