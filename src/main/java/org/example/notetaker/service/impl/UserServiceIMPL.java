package org.example.notetaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.dao.UserDAO;
import org.example.notetaker.dto.UserDTO;
import org.example.notetaker.entity.UserEntity;
import org.example.notetaker.service.UserService;
import org.example.notetaker.util.AppUtil;
import org.example.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final Mapping mapping;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        userDAO.save(mapping.convertToUserEntity(userDTO));
        return "User Saved Successfully";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/users/USER-825c1b6f-49fa-4db9-8db6-d8716c64d0bc
        if (userDAO.existsById(userId)) {
            userDAO.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntityByUserId = userDAO.getUserEntitiesByUserId(userId);
        return mapping.convertToUserDTO(userEntityByUserId);    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }
}
