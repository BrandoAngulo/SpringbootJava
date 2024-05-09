package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.Controllers.DTO.UserDTO;
import com.qualitysales.posinventory.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> listUsers();
    UserDTO listUser(Integer id);
    UserDTO saveUser(User user);
    User updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
}
