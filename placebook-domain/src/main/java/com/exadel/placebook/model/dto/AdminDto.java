package com.exadel.placebook.model.dto;


import java.util.List;

public class AdminDto {
    private List<UserDto> users;

    public AdminDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
