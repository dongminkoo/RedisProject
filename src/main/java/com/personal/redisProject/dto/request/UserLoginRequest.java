package com.personal.redisProject.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    @NonNull
    private String id;
    @NonNull
    private String password;
}
