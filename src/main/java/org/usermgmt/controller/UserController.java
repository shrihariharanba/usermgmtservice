package org.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.usermgmt.model.User;
import org.usermgmt.service.UserService;

import java.util.List;

/**
 * User Operation
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get User details
     * @param id userId
     * @return user
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * Get All users
     * @return user list
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * Create User
     * @param user
     * @return new user
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Update User
     * @param user
     * @return updated user detials
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * Delete user
     * @param user
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody User user) {
        userService.deleteUser(user);
    }
}
