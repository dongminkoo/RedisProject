package com.personal.redisProject.service;

import com.personal.redisProject.dto.UserDTO;
import com.personal.redisProject.exception.DuplicateIdException;
import com.personal.redisProject.mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.personal.redisProject.utils.SHA256Util.encryptSHA256;

@Log4j2
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserServiceImpl(UserProfileMapper userProfileMapper){
        this.userProfileMapper = userProfileMapper;
    }

    /* 회원가입 */
    @Override
    public void register(UserDTO userProfile) {
        boolean dupleIdResult = isDuplicatedId(userProfile.getUserId());
        if(dupleIdResult){
            throw new DuplicateIdException("중복된 아이디 입니다.");
        }
        userProfile.setCreateTime(new Date());
        userProfile.setPassword(encryptSHA256(userProfile.getPassword()));
        int insertCount = userProfileMapper.register(userProfile);

        if(insertCount != 1){
            log.error("insertMember Error :{}", userProfile);
            throw new RuntimeException("회원가입에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    /* 로그인 */
    @Override
    public UserDTO login(String id, String password) {
        String cryptoPassword = encryptSHA256(password); // User가 입력한 password 를 암호화 해 찾기
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        return memberInfo;
    }

    @Override
    public boolean isDuplicatedId(String id) { // id 중복 확인
        return userProfileMapper.idCheck(id) == 1;
    }

    /* User 정보 확인 */
    @Override
    public UserDTO getUserInfo(String userId) { // User 정보 확인
        return userProfileMapper.getUserProfile(userId);
    }

    /* User 정보 업데이트 */
    @Override
    public void updatePassword(String id, String beforePassword, String afterPassword) { // pw 업데이트
        String cryptoPassword = encryptSHA256(beforePassword);
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if(memberInfo != null){
            memberInfo.setPassword(encryptSHA256(afterPassword));
            int updateCount = userProfileMapper.updatePassword(memberInfo);
        }else{
            log.error("updatePassword Error :{}", memberInfo);
            throw new IllegalArgumentException("비밀번호 변경에 실패하였습니다. 다시 시도해주세요." + memberInfo);

        }
    }

    /* User 정보 삭제 */
    @Override
    public void deleteId(String id, String password) { // 계정 삭제
        String cryptoPassword = encryptSHA256(password);
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if(memberInfo != null){
            userProfileMapper.deleteUserProfile(memberInfo.getUserId());
        }else{
            log.error("deleteId Error :{}", memberInfo);
            throw new RuntimeException("계정 삭제에 실패하였습니다. 다시 시도해주세요." + memberInfo);
        }

    }
}
