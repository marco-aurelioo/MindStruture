package com.tiozao.services;

import com.tiozao.entities.RoleEntity;
import com.tiozao.entities.UserEntity;
import com.tiozao.model.UserModel;
import com.tiozao.repositories.RoleRepository;
import com.tiozao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<RoleEntity>(roleRepository.findAll()));

        UserEntity entity = new UserEntity();
        entity.setLogin(user.getLogin());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles());

        userRepository.save(entity);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
