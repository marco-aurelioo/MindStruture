package com.tiozao.services;

import com.tiozao.entities.UserEntity;
import com.tiozao.model.UserModel;

import java.util.List;

public interface UserService {

    void save(UserModel user);

    UserEntity findByEmail(String email);

    UserModel getSessionUser();

    List<String> updateUser(UserModel userForm);
}
