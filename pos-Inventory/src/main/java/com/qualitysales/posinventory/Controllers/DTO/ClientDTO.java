package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.City;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ClientDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private City city;
    private String residence;
    private String cellPhone;
    private String email;
    private String estate;

}
