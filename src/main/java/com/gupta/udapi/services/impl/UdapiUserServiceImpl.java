package com.gupta.udapi.services.impl;

import com.gupta.udapi.dtos.UdapiUserDto;
import com.gupta.udapi.entities.UdapiUserEntity;
import com.gupta.udapi.exception.UserAlreadyExistsException;
import com.gupta.udapi.exception.UserNotFoundException;
import com.gupta.udapi.json.JwtTokenJson;
import com.gupta.udapi.json.LoginDetailsJson;
import com.gupta.udapi.json.SignUpDetailsJson;
import com.gupta.udapi.jwt.JwtUtility;
import com.gupta.udapi.mapper.UdapiUserMapper;
import com.gupta.udapi.repositories.UdapiUserRepository;
import com.gupta.udapi.services.UdapiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amit
 */
@Service
public class UdapiUserServiceImpl implements UdapiUserService {


    @Autowired
    private UdapiUserRepository udapiUserRepository;

    @Autowired
    private UdapiUserMapper userMapper;

    @Autowired
    private JwtUtility jwtUtility;


    @Override
    public UdapiUserDto getUdapiUserByUserName(String userName) {
        UdapiUserEntity userEntity = udapiUserRepository.getUserName(userName);
        return userMapper.getDtoFromEntity(userEntity);
    }

    @Override
    public JwtTokenJson doLogin(LoginDetailsJson loginDto) {
        UdapiUserDto userDto = userMapper.getDtoFromEntity(udapiUserRepository.getUserName(loginDto.getUserName()));

        /*
         * Comparing supplied password during login and stored hashed and salted password.
         * If password is wrong, an exception is thrown.
         */
        if (!BCrypt.checkpw(loginDto.getPassword(), userDto.getPasswordHash())) {
            throw new UserNotFoundException("The username or password is incorrect.");
        }

        // Create a new JWT token and send it to the user.
        JwtTokenJson jwtTokenJson = new JwtTokenJson();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userDto.getUserName());
        claims.put("type", userDto.getType());
        String token = jwtUtility.getNewTokenFromClaims(claims);
        jwtTokenJson.setToken(token);
        return jwtTokenJson;
    }

    @Override
    public UdapiUserDto doSignUp(SignUpDetailsJson signUpDetailsJson) {

        // Check if user already exists.
        if (!udapiUserRepository.checkIfUdapiUserExists(signUpDetailsJson.getUserName())) {// Creating a hashed and salted password with the password supplied during sign up
            signUpDetailsJson.setPassword(BCrypt.hashpw(signUpDetailsJson.getPassword(), BCrypt.gensalt()));
            UdapiUserEntity brandUserEntity = udapiUserRepository.addNewUser(userMapper.getEntityFromSignUp(signUpDetailsJson));
            return userMapper.getDtoFromEntity(brandUserEntity);
        } else {
            throw new UserAlreadyExistsException("The brand with that ID and name combination already exists.");
        }

    }
}
