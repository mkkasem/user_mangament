package com.dev.service.impl;

import com.dev.dao.UserDao;
import com.dev.service.UserService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private ModelMapper mapper;


    @Override
    public List getAllUsersExceptCurrentOne(String username) {
        var users = userDao.getAllUsersExceptCurrentOne(username);
        return mapper.map(users, ArrayList.class);

    }


}
