package com.tiozao.repositories;

import com.tiozao.entities.EmailEntity;
import com.tiozao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity,Integer> {



}
