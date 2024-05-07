package com.qualitysales.posinventory.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String code;
    private String email;
    private String state;

}
