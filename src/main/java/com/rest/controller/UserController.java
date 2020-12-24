package com.rest.controller;

import com.rest.entity.User;
import com.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Request body example:
     * [
     *   {
     *     "id": 1,
     *     "fullName": "Name Surname",
     *     "address": "Address",
     *     "phone": "465-879-2884",
     *     "regionSupport": "BLR"
     *   },
     *   {
     *     "id": 2,
     *     "fullName": "John Lennon",
     *     "address": "706 Cremin Camp, New Vancefurt, FL 19771-6605",
     *     "phone": "1-499-872-3167",
     *     "regionSupport": "RUS"
     *   }
     * ]
     *
     * CREATE
     * POST HTTP method
     *
     * Create a batch of Users
     * @param users
     * @return HttpStatus.CREATED if batch of Users are created
     */
    @PostMapping(value = "/users")
    public ResponseEntity<User> createUsers(@RequestBody List<User> users) {
        userService.addUsers(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * READ
     * GET HTTP method
     * @return all Users and HttpStatus.OK if Users are found, otherwise HttpStatus.NOT_FOUND
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userService.getAllUsers();
        return !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * READ
     * GET HTTP method
     *
     * @param id
     * @return User and HttpStatus.OK if User is found, otherwise HttpStatus.NOT_FOUND
     */
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") int id) {
        final Optional<User> user = userService.getUserById(id);
        return user.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Request body example:
     * [
     *   {
     *     "id": 1,
     *     "fullName": "Updated Name",
     *     "address": "Updated Address",
     *     "phone": "465-879-2884",
     *     "regionSupport": "USA"
     *   },
     *   {
     *     "id": 2,
     *     "fullName": "John Lennon",
     *     "address": "706 Cremin Camp, New Vancefurt, FL 19771-6605",
     *     "phone": "1-499-872-3167",
     *     "regionSupport": "BLR"
     *   }
     * ]
     *
     * UPDATE
     * PUT HTTP method
     *
     * @param users
     * @return updated Users and HttpStatus.OK if Users are found and updated, otherwise HttpStatus.NOT_MODIFIED
     */
    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUsers(@RequestBody List<User> users) {
        return userService.updateUsers(users)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Request body example:
     * {
     *   "id": 1,
     *   "fullName": "Updated Name",
     *   "address": "Updated Address",
     *   "phone": "465-879-2884",
     *   "regionSupport": "BLR"
     * }
     *
     * UPDATE
     * PUT HTTP method
     *
     * @param id
     * @param user
     * @return updated User and HttpStatus.OK if User is found and updated, otherwise HttpStatus.NOT_MODIFIED
     */
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") int id, @RequestBody User user) {
        return userService.updateUserById(user, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * DELETE
     * DELETE HTTP method
     *
     * @return HttpStatus.OK and delete all Users
     */
    @DeleteMapping(value = "/users")
    public ResponseEntity<User> deleteAllUsers() {
        return userService.deleteAllUsers()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * DELETE
     * DELETE HTTP method
     *
     * @param id
     * @return HttpStatus.OK and delete User by id
     */
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") int id) {
        return userService.deleteUserById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
