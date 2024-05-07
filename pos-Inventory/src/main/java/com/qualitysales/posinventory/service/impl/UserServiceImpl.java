package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.UserDTO;
import com.qualitysales.posinventory.model.User;
import com.qualitysales.posinventory.repository.UserRepository;
import com.qualitysales.posinventory.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> userList = userRepository.findAll();
        try {
            return userList.stream().map(user1 -> UserDTO.builder()
                            .id(user1.getId())
                            .name(user1.getName())
                            .lastName(user1.getLastName())
                            .code(user1.getCode())
                            .state(user1.getState())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error("listUsers = " + userList);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public UserDTO listUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        try {
            return UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .code(user.getCode())
                    .state(user.getState())
                    .build();
        } catch (Exception e) {
            log.error("listUsers = " + user);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public UserDTO saveUser(User user) {
        if (user.getId() == null) {
            log.error("saveUser = " + user.toString());
            throw new IllegalArgumentException("User id is null ");
        }
        try {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .code(user.getCode())
                    .state(user.getState())
                    .build();
            userRepository.save(user);
            return userDTO;

        } catch (Exception e) {
            log.error("saveUser = " + user.toString());
            throw new IllegalArgumentException("Uncontroller error ");
        }
    }

    @Override
    public UserDTO updateUser(Integer id, User user) {
        User existUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(existUser.getId());
            userDTO.setName(existUser.getLastName());
            userDTO.setCode(existUser.getCode());
            userDTO.setEmail(existUser.getEmail());
            userDTO.setState(existUser.getState());
            saveUser(existUser);
            return userDTO;


        } catch (Exception e) {
            log.error("updateUser = " + user.toString());
            throw new IllegalArgumentException("Uncontroller error ");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        User existUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        try {
            userRepository.deleteById(existUser.getId());

        } catch (Exception e) {
            log.error("deleteUser = " + existUser.toString());
            throw new IllegalArgumentException("Uncontroller error ");
        }

    }
}
