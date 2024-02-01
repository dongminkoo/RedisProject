package com.personal.redisProject.dto.response;

import com.personal.redisProject.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private UserDTO userDTO;
}
