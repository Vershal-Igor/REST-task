package com.rest.service;

import com.rest.entity.User;
import com.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> addUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public boolean updateUsers(List<User> users) {
        if (isNotEmpty(users)) {
            for (User user : users) {
                updateUserById(user, user.getId());
            }
            return true;
        }
        return false;
    }

    public boolean updateUserById(User user, int id) {
        Optional<User> userFromDb = getUserById(id);
        if (userFromDb.isPresent()) {
            User updatedUser = userFromDb.get();
            updatedUser.setFullName(defaultIfNull(user.getFullName(), updatedUser.getFullName()));
            updatedUser.setAddress(defaultIfNull(user.getAddress(), updatedUser.getAddress()));
            updatedUser.setPhone(defaultIfNull(user.getPhone(), updatedUser.getPhone()));
            updatedUser.setRegionSupport(defaultIfNull(user.getRegionSupport(), updatedUser.getRegionSupport()));
            userRepository.save(updatedUser);
            return true;
        }
        return false;
    }

    public boolean deleteAllUsers() {
        if (isNotEmpty(getAllUsers())) {
            userRepository.deleteAll();
            return true;
        }
        return false;
    }

    public boolean deleteUserById(int id) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
