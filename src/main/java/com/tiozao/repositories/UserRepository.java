package com.tiozao.repositories;

import com.tiozao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByLogin(String login);

}

