package com.tiozao.model;

import java.util.List;

public class FriendList {

    private UserModel user;
    private List<FriendList> friends;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<FriendList> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendList> friends) {
        this.friends = friends;
    }
}
