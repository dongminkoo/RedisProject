package com.personal.redisProject.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.personal.redisProject.dto.UserDTO;

@Mapper
public interface UserProfileMapper {

    public UserDTO getUserProfile(@Param("userId") String userId); // id 값으로 User 정보 가져오기
    int insertUserProfile(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("address") String address); // User 정보 저장
    int updateUserProfile(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("address") String address); // User 정보 업데이트
    int deleteUserProfile(@Param("id") String id); //User 정보 삭제
    public int register(UserDTO userDTO); // User 정보 등록
    public UserDTO findByIdAndPassword(@Param("id") String id, @Param("password") String password); // Id 와 password 로 User 정보 찾기
    public UserDTO findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password); // userId 와 password 로 User 정보 찾기
    int idCheck(String id); //id 중복 확인
    public int updatePassword(UserDTO userDTO); // password 변경 업데이트
    public int updateAddress(UserDTO userDTO); // address 변경 업데이트


}
