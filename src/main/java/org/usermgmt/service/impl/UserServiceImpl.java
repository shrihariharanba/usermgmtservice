package org.usermgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usermgmt.config.ProductException;
import org.usermgmt.entity.UserEntity;
import org.usermgmt.model.User;
import org.usermgmt.repository.UserRepository;
import org.usermgmt.service.UserService;
import org.usermgmt.util.ExceptionType;
import org.usermgmt.util.SecurityUtil;
import org.usermgmt.util.UserManagementMapper;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    UserManagementMapper mapper;

    @Autowired
    SecurityUtil securityUtil;

    /**
     * Create new user
     * @param user
     * @return new user
     */
    @Override
    public User createUser(User user) {
        user.setCreatedDate(new Date());
        user.setPassword(securityUtil.encode(user.getPassword()));
        if (userRepo.findByEmailAddress(user.getEmailAddress()) != null) {
            new ProductException(ExceptionType.USER_CONFLICT.getCode(),
                    ExceptionType.USER_CONFLICT.getType(),
                    "Email Address already in use");
        }
        UserEntity result = userRepo.save(mapper.map(user, UserEntity.class));
        return mapper.map(result, User.class);
    }

    /**
     * Deletes the user
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        userRepo.deleteById(user.getUserId());
    }

    /**
     * Update the user
     * @param user
     * @return updated user details
     * @throws ProductException
     */
    @Override
    public User updateUser(User user) throws ProductException {
        UserEntity userEntity = userRepo.getById(user.getUserId());
        if (userEntity != null) {
            userEntity.setName(user.getName());
            userEntity.setUpdatedDate(new Date());
            UserEntity userEmail = userRepo.findByEmailAddress(user.getEmailAddress());
            if (userEmail != null && userEmail.getUserId() != user.getUserId()) {
                new ProductException(ExceptionType.USER_CONFLICT.getCode(),
                        ExceptionType.USER_CONFLICT.getType(),
                        "Email Address already in use");
            }
            userEntity.setEmailAddress(user.getEmailAddress());
            userEntity = userRepo.save(userEntity);
            return mapper.map(userEntity, User.class);
        }
        throw new ProductException(ExceptionType.USER_VALIDATION.getCode(),
                ExceptionType.USER_VALIDATION.getType(),
                user.getUserId() + " not present");
    }

    /**
     * Get user details
     * @param userId
     * @return user details
     */
    @Override
    public User getUser(Long userId) {
        UserEntity userEntity = userRepo.getById(userId);
        return mapper.map(userEntity, User.class);
    }

    /**
     * Get all user list
     * @return user list
     */
    @Override
    public List<User> getAll() {
        List<UserEntity> userEntity = userRepo.findAll();
        List<User> userList = new ArrayList<>();
        userEntity.forEach(e -> {
            userList.add(mapper.map(e, User.class));
        });
        return userList;
    }

    /**
     * Method to find user by email address
     * @param emailAddress
     * @return user
     */
    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return mapper.map(userRepo.findByEmailAddress(emailAddress), User.class);
    }

    /**
     * Updates last login alone in the user
     * @param user
     * @return user
     */
    @Override
    public User updateLastLogin(User user) {
        UserEntity updatedUser = userRepo.getById(user.getUserId());
        updatedUser.setLastLogin(new Date());
        return mapper.map(userRepo.save(updatedUser), User.class);
    }

    public List<User> getSelectedUser(Long[] userIds) {
        List<UserEntity> userEntityList = userRepo.findByUserIds(userIds);
        List<User> userList = new ArrayList<>();
        userEntityList.forEach(e -> {
            userList.add(mapper.map(e, User.class));
        });
        return userList;
    }

}
