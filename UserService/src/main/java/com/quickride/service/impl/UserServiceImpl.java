package com.quickride.service.impl;

import com.quickride.dto.UserCreateRequestDto;
import com.quickride.dto.UserDto;
import com.quickride.entity.User;
import com.quickride.entity.UserType;
import com.quickride.repo.UserRepository;
import com.quickride.service.UserModelMapper;
import com.quickride.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;

    @Override
    @Transactional
    public UserDto registerUser(UserCreateRequestDto createRequestDto, UserType userType) {
        User user = new User();

        user.setName(createRequestDto.getName());
        user.setPhone(createRequestDto.getPhone());
        user.setEmail(createRequestDto.getEmail());
        user.setUserType(userType);

        User savedUser = userRepository.save(user);

        return userModelMapper.toDto(savedUser);
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(userModelMapper::toDto).orElse(null);
    }
}
