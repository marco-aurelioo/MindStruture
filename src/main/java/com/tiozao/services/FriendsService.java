package com.tiozao.services;

import com.tiozao.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {


    public Page<UserModel> findMyFriends(UserModel sessionUser,Integer page, Integer size) {

        return Page.empty();
    }
}
