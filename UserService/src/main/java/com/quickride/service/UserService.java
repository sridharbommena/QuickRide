package com.quickride.service;

import com.quickride.dto.UserCreateRequestDto;
import com.quickride.dto.UserDto;
import com.quickride.entity.UserType;

public interface UserService {
    UserDto registerUser(UserCreateRequestDto createRequestDto, UserType userType);
    UserDto findUserById(Long id);
}
