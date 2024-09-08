package org.example.notetaker.controller;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.dto.UserDTO;
import org.example.notetaker.service.UserService;
import org.example.notetaker.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

