package com.personal.redisProject.service;


import com.personal.redisProject.dto.UserDTO;

public interface UserService {

    void register(UserDTO userProfile); //User 정보 등록
    UserDTO login(String id, String password); //User Login
    boolean isDuplicatedId(String id); // ID 중복 확인
    UserDTO getUserInfo(String userId); //User 정보 확인
    void updatePassword(String id, String beforePassword, String afterPassword); // pw 업데이트
    void deleteId(String id, String password); // 계정 삭제


}
