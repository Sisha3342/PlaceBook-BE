package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class UserSearchDto {
    Long id;
    String name;
    String surname;
    String photoUrl;
}
