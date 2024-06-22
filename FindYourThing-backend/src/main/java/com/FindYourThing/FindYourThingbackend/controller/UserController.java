package com.FindYourThing.FindYourThingbackend.controller;

import com.FindYourThing.FindYourThingbackend.dto.UserDTO;
import com.FindYourThing.FindYourThingbackend.model.User;
import com.FindYourThing.FindYourThingbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/saveuser")
    public UserDTO saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getallusers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/updateuser/{id}")
    public UserDTO updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/deleteuser/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
