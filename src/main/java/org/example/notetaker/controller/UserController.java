package org.example.notetaker.controller;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.dto.UserDTO;
import org.example.notetaker.service.UserService;
import org.example.notetaker.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    // add health check
    @GetMapping("/health")
    public String checkHealth() {
        return "User controller is running...";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic) {

        // handle profile picture
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);

        // Build the user object
        var buildUserDTO = new UserDTO();
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProfilePic);

        // Send the user object to the service
        return new ResponseEntity<>(userService.saveUser(buildUserDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        boolean deleted = userService.deleteUser(id);
        if(deleted){
            return new ResponseEntity<>("User deleted successfully" ,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(
            @PathVariable ("id") String id,
            @RequestPart("updateFirstName") String updateFirstName,
            @RequestPart ("updateLastName") String updateLastName,
            @RequestPart ("updateEmail") String updateEmail,
            @RequestPart ("updatePassword") String updatePassword,
            @RequestPart ("updateProfilePic") String updateProfilePic
    ){
        String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
        var updateUser = new UserDTO();
        updateUser.setUserId(id);
        updateUser.setFirstName(updateFirstName);
        updateUser.setLastName(updateLastName);
        updateUser.setPassword(updatePassword);
        updateUser.setEmail(updateEmail);
        updateUser.setProfilePic(updateBase64ProfilePic);
        return userService.updateUser(updateUser)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

