package com.qualitysales.posinventory.mapper;

import com.qualitysales.posinventory.Controllers.DTO.UserDTO;
import com.qualitysales.posinventory.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDTO convertToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
