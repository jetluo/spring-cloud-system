package com.cloud.kjetboy.server.sso.dao;


import com.cloud.kjetboy.server.sso.entity.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);

}
