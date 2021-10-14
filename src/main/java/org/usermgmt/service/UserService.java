package org.usermgmt.service;

import org.usermgmt.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);

    public void deleteUser(User user);

    public User updateUser(User user);

    public User getUser(Long userId);

    public List<User> getAll();

    public User findUserByEmailAddress(String emailAddress);

    public User updateLastLogin(User user);
}
