package org.example.notetaker.service.impl;

import org.example.notetaker.dto.UserDTO;
import org.example.notetaker.service.UserService;

import java.util.List;

public class UserServiceIMPL implements UserService {

    @Override
    public String saveUser(UserDTO userDTO) {
        return "";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }
}
