package com.quickride.controller;

import com.quickride.dto.UserCreateRequestDto;
import com.quickride.dto.UserDto;
import com.quickride.entity.UserType;
import com.quickride.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    POST /api/users/register-rider — registers a new rider (name, email, phone)
    @PostMapping("register-rider")
    public ResponseEntity<UserDto> registerRider(@RequestBody UserCreateRequestDto createRequestDto){
        UserDto userDto = userService.registerUser(createRequestDto, UserType.RIDER);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

//    POST /api/users/register-driver — registers a new driver (name, email, phone)
    @PostMapping("register-driver")
    public ResponseEntity<UserDto> registerDriver(@RequestBody UserCreateRequestDto createRequestDto){
        UserDto userDto = userService.registerUser(createRequestDto, UserType.DRIVER);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

//    GET /api/users/{id} — fetch user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id){
        UserDto userById = userService.findUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

}
