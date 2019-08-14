package com.tiozao.services;

import com.tiozao.entities.RoleEntity;
import com.tiozao.entities.UserEntity;
import com.tiozao.exception.UserSessionNotFound;
import com.tiozao.model.UserModel;
import com.tiozao.repositories.RoleRepository;
import com.tiozao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entity.setRoles(user.getRoles());
        entity.setEmail(user.getEmail());
        RoleEntity role = roleRepository.findByRole("USER");
        if(role != null) {
            entity.getRoles().add(role);
        }
        userRepository.save(entity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel getSessionUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            return convertPrincipalToUserModel((UserDetails) principal);//
        }else{
            UserModel user = new UserModel();
            return user;
        }

    }

    @Override
    public List<String> updateUser(UserModel userForm) {
        List<String> errors = new ArrayList<String>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByEmail(((UserDetails) principal).getUsername());
        user.setAvatarUrl(userForm.getAvatarUrl());
        user.setName(userForm.getName());
        try {
            userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
            errors.add("Erro ao tentar salvar os dados.(usuario)");
        }
        return errors;
    }


    private UserModel convertPrincipalToUserModel(UserDetails principal) {
        UserModel retorno = new UserModel();
        if(principal == null)
            return retorno;
        UserEntity user = userRepository.findByEmail(principal.getUsername());
        retorno.setName(user.getName());
        retorno.setEmail(user.getEmail());
        if(user.getAvatarUrl() != null) {
            retorno.setAvatarUrl(user.getAvatarUrl());
        }else{
            retorno.setAvatarUrl("../img/avatarDefault.png");
        }
        return retorno;
    }
}
