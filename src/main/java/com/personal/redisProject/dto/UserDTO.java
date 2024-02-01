package com.personal.redisProject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {

    public static boolean hasNullDataBeforeSignUp(UserDTO userDTO) {
        return userDTO.getUserID() == null || userDTO.getPassword() == null || userDTO.getNickName() == null;
    }

    public enum Status{
        DEFAULT,ADMIN,DELETED
    }

    private int id;
    private String userID;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private boolean iisWithDraw;
    private Date createTime;
    private Date updateTme;
    private Status status;
}
