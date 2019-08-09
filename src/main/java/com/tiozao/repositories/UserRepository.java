package com.tiozao.repositories;

import com.tiozao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);

}

