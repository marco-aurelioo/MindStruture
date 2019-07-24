package com.tiozao.repositories;

import com.tiozao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("select ue.user from EmailEntity ue where ue.email = :email ")
    UserEntity findByEmail(String email);

}

