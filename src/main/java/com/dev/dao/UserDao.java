package com.dev.dao;

import com.dev.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<UserModel> getUserByUsername(String username);

    List<UserModel> getAllUsersExceptCurrentOne(String username);

    Boolean saveOrUpdate(Object model);

    Optional<UserModel> getUserById(long id);
}
