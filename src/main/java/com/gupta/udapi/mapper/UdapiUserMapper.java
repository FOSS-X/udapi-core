package com.gupta.udapi.mapper;

import com.gupta.udapi.dtos.UdapiUserDto;
import com.gupta.udapi.entities.UdapiUserEntity;
import com.gupta.udapi.json.SignUpDetailsJson;
import org.springframework.stereotype.Service;

/**
 * @author Amit
 */
@Service
public class UdapiUserMapper {

    public UdapiUserDto getDtoFromEntity(UdapiUserEntity userEntity) {

        return new UdapiUserDto.Builder()
                .setUserName(userEntity.getName())
                .setPasswordHash(userEntity.getPasswordHash())
                .setType(userEntity.getType())
                .build();
    }

    public UdapiUserEntity getEntityFromDto(SignUpDetailsJson signUpDetailsJson) {

        return new UdapiUserEntity.Builder()
                .setName(signUpDetailsJson.getUserName())
                .setPasswordHash(signUpDetailsJson.getPassword())
                .setType(signUpDetailsJson.getType())
                .setStatus(true)
                .build();
    }
}
