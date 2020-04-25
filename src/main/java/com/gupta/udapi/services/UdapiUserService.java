package com.gupta.udapi.services;

import com.gupta.udapi.dtos.UdapiUserDto;
import com.gupta.udapi.json.JwtTokenJson;
import com.gupta.udapi.json.LoginDetailsJson;
import com.gupta.udapi.json.SignUpDetailsJson;
import org.springframework.stereotype.Service;

/**
 * @author Amit
 */
public interface UdapiUserService {

    UdapiUserDto getUdapiUserByUserName(String userName);
    JwtTokenJson doLogin(LoginDetailsJson loginDto);
    UdapiUserDto doSignUp(SignUpDetailsJson signUpDetailsJson);
}
