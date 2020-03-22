package com.gupta.udapi.controllers;

import com.gupta.udapi.dtos.UdapiUserDto;
import com.gupta.udapi.json.JwtTokenJson;
import com.gupta.udapi.json.LoginDetailsJson;
import com.gupta.udapi.json.SignUpDetailsJson;
import com.gupta.udapi.services.UdapiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amitkumargupta
 */

@RestController
@RequestMapping("/v1/auth/")
public class UdapiUserAuthController {

    @Autowired
    private UdapiUserService udapiUserService;

    /*
     * Authenticates a user and returns a jwt token.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtTokenJson> doLogin(@RequestBody LoginDetailsJson loginDto) {
        JwtTokenJson jwtTokenJson = udapiUserService.doLogin(loginDto);
        return new ResponseEntity<JwtTokenJson>(jwtTokenJson, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UdapiUserDto> doSignUp(@RequestBody SignUpDetailsJson signUpDetailsJson) {
        UdapiUserDto udapiUserDto = udapiUserService.doSignUp(signUpDetailsJson);

        //Removing hashed password from response to UI
        udapiUserDto.setPasswordHashToEmpty();
        return new ResponseEntity<UdapiUserDto>(udapiUserDto, HttpStatus.OK);
    }
}
