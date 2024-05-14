package com.dev.facade.impl;

import com.dev.dao.UserDao;
import com.dev.dto.UpdateUserRequest;
import com.dev.dto.UserRequest;
import com.dev.dto.UserResponse;
import com.dev.enums.Role;
import com.dev.facade.UserFacade;
import com.dev.model.UserModel;
import com.dev.util.CustomPasswordEncoder;
import com.dev.util.GenerateResponse;
import com.dev.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class UserFacadeImpl implements UserFacade {
    private static final Logger LOG = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Resource
    private UserDao userDao;

    @Resource
    private ModelMapper mapper;

    @Resource
    private CustomPasswordEncoder passwordEncoder;
    @Resource
    private Utils utils;

    @Resource
    private SessionFactory sessionFactory;


    @Override
    public ResponseEntity<?> createNewUser(UserRequest userRequest) throws JsonProcessingException {
        try {
            var isUsernameAlreadyTaken = isUsernameAlreadyTaken(userRequest.getUsername());
            if (isUsernameAlreadyTaken) {
                return GenerateResponse.badRequest("username already taken please select new one", null);
            }
            UserModel userModel = mapper.map(userRequest, UserModel.class);
            userModel.setRole(userRequest.isAdmin() ? Role.ADMIN : Role.USER);
            userModel.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userDao.saveOrUpdate(userModel);
            String responseMessage = utils.getMessageLocalized("user.created.successfully");
            return GenerateResponse.created(responseMessage, null);
        } catch (Exception e) {
            LOG.error("createNewUser: ", e);
            return utils.generateInternalServerErrorMessage();
        }
    }

    @Override
    public boolean isUsernameAlreadyTaken(String username) {
        return userDao.getUserByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUserById(long id) throws JsonProcessingException {
        try {
            var optionalUser = userDao.getUserById(id);
            if (optionalUser.isPresent()) {
                Session session = sessionFactory.getCurrentSession();
                session.delete(optionalUser.get());
                return GenerateResponse.success(utils.getMessageLocalized("user.deleted.successfully"), null);
            } else {
                return GenerateResponse.badRequest(utils.getMessageLocalized("user.not.found"), null);

            }
        } catch (Exception e) {
            LOG.error("createNewUser: ", e);
            return utils.generateInternalServerErrorMessage();
        }

    }

    @Override
    public ResponseEntity<?> getUserById(long id) throws JsonProcessingException {
        try {
            var optionalUser = userDao.getUserById(id);
            if (optionalUser.isPresent()) {
                var user = optionalUser.get();
                var response = mapper.map(optionalUser.get(), UserResponse.class);
                response.setAdmin(user.getRole().equals(Role.ADMIN));
                return GenerateResponse.success(null, response);
            } else {
                return GenerateResponse.badRequest(utils.getMessageLocalized("user.not.found"), null);

            }
        } catch (Exception e) {
            LOG.error("getUserById: ", e);
            return GenerateResponse.error(utils.getMessageLocalized("label.internal.server.error"), null);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(UpdateUserRequest updateUserRequest) throws JsonProcessingException {
        try {
            var optionalUser = userDao.getUserById(updateUserRequest.getId());
            var isUserNameAlreadyTaken = isUsernameAlreadyTaken(updateUserRequest.getUsername());
            if (!optionalUser.isPresent()) {
                return GenerateResponse.badRequest(utils.getMessageLocalized("user.not.found"), null);
            }

            var userToUpdate = optionalUser.get();
            if (!updateUserRequest.getUsername().equals(userToUpdate.getUsername()) && isUserNameAlreadyTaken) {
                return GenerateResponse.badRequest("username already taken please select new one", null);
            }
            userToUpdate.setUsername(updateUserRequest.getUsername());
            userToUpdate.setEmail(userToUpdate.getEmail());
            userToUpdate.setPhoneNumber(updateUserRequest.getPhoneNumber());
            userToUpdate.setBirthDate(updateUserRequest.getBirthDate());
            userToUpdate.setRole(updateUserRequest.isAdmin() ? Role.ADMIN : Role.USER);
            userDao.saveOrUpdate(userToUpdate);
            String responseMessage = utils.getMessageLocalized("user.update.successfully");
            return GenerateResponse.success(responseMessage, null);
        } catch (Exception e) {
            LOG.error("updateUser: ", e);
            return GenerateResponse.error(utils.getMessageLocalized("label.internal.server.error"), null);
        }

    }


}
