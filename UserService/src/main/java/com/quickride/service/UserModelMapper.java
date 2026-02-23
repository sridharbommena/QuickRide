package com.quickride.service;

import com.quickride.dto.UserDto;
import com.quickride.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserModelMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

}
