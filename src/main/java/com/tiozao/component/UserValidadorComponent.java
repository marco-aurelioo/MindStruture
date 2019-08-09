package com.tiozao.component;


import com.tiozao.entities.UserEntity;
import com.tiozao.model.UserModel;
import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.Set;

@Component
public class UserValidadorComponent {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    public void validate(Object o, List<String> errors) {
        UserModel user = (UserModel) o;

        if(user.getEmail().isEmpty()){
            errors.add("Email invalido.");
        }
        if(user.getEmail().isEmpty()){
            errors.add("senha vazia.");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.add("email ja existente na nossa base");
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.add( "senha inválida tem que ter mais de 6 caracters");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.add("confirmação de senha não corresponde a senha");
        }
    }

}
