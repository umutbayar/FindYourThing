package com.FindYourThing.FindYourThingbackend.service;

import com.FindYourThing.FindYourThingbackend.dto.UserDTO;
import com.FindYourThing.FindYourThingbackend.exception.UserAlreadyExistException;
import com.FindYourThing.FindYourThingbackend.exception.UserNotFoundException;
import com.FindYourThing.FindYourThingbackend.model.User;
import com.FindYourThing.FindYourThingbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserDTO saveUser(User user) {

        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            ModelMapper modelMapper = new ModelMapper();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            return userDTO;
        }
        else {
            throw new UserAlreadyExistException(user.getUsername());
        }
    }

    public List<UserDTO> getAllUsers() {

        List<User> userList = userRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<UserDTO> userDTOList = Arrays.asList(modelMapper.map(userList, UserDTO[].class));

        return userDTOList;
    }

    public UserDTO getUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public UserDTO updateUser(User newUser, Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        if (newUser.getUsername() != null) {
            user.setUsername(newUser.getUsername());
        }
        if (newUser.getPassword() != null) {
            user.setPassword(newUser.getPassword());
        }
        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }
        if (newUser.getSurname() != null) {
            user.setSurname(newUser.getSurname());
        }
        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }
        if (newUser.getPhoneNumber() != null) {
            user.setPhoneNumber(newUser.getPhoneNumber());
        }
        if (newUser.getAddress() != null) {
            user.setAddress(newUser.getAddress());
        }

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        userRepository.save(user);

        return userDTO;
    }

    public UserDTO deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        userRepository.delete(user);

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

}
