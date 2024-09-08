package org.example.notetaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.customObj.UserErrorResponse;
import org.example.notetaker.customObj.UserResponse;
import org.example.notetaker.dao.UserDAO;
import org.example.notetaker.dto.UserDTO;
import org.example.notetaker.entity.UserEntity;
import org.example.notetaker.exception.UserNotFoundException;
import org.example.notetaker.service.UserService;
import org.example.notetaker.util.AppUtil;
import org.example.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        UserEntity savedUser = userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser != null && savedUser.getUserId() != null ) {
            return "User saved successfully";
        }else {
            return "Save failed";
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
            userDAO.save(tmpUser.get());
        }
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
    public UserResponse getSelectedUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity userEntityByUserId = userDAO.getUserEntitiesByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userDAO.findAll();
        System.out.println(users);
        return mapping.convertUserToDTO(users);
    }
}
