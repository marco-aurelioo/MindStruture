package com.tiozao.services;

import com.tiozao.entities.UserEntity;
import com.tiozao.model.UserModel;

public interface UserService {

    void save(UserModel user);

    UserEntity findByEmail(String email);

}
