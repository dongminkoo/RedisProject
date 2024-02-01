package com.personal.redisProject.controller;

import com.personal.redisProject.dto.UserDTO;
import com.personal.redisProject.dto.response.LoginResponse;
import com.personal.redisProject.dto.response.UserInfoResponse;
import com.personal.redisProject.service.UserServiceImpl;
import com.personal.redisProject.utils.SessionUtil;
import com.personal.redisProject.dto.request.UserLoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserServiceImpl userService;
    private static final ResponseEntity<LoginResponse> FAIL_RESPONSE = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);

    public UserController(UserServiceImpl userService) {this.userService = userService;}

    @PostMapping("signUp") // 회원가입
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDTO userDTO){
        if(UserDTO.hasNullDataBeforeSignUp(userDTO)){
            throw new NullPointerException("회원가입 정보를 모두 입력해주세요.");
        }
        userService.register(userDTO);
    }

    @PostMapping("login") // 로그인
    public HttpStatus login(@RequestBody UserLoginRequest userLoginRequest, HttpSession session){
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = userLoginRequest.getUserId();
        String password = userLoginRequest.getPassword();
        UserDTO userInfo = userService.login(id, password);

        if(userInfo == null){
            return HttpStatus.NOT_FOUND; //회원정보가 없을 시 NOT_FOUND
        }else if(userInfo != null){
            LoginResponse loginResponse = LoginResponse.success(userInfo);
            if(userInfo.getStatus() == (UserDTO.Status.ADMIN)){
                SessionUtil.setLoginAdminId(session,id);
            else{
                SessionUtil.setLoginMemberId(session,id);
            }
            responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else{
                throw new RuntimeException("로그인에 실패하였습니다. 사용자 정보가 없거나 삭제된 사용자 입니다.");
            }
            return HttpStatus.OK;
    }

    @PutMapping("logout") // 로그아웃
    public void logout(HttpSession session){
        SessionUtil.clear(session);
    }

    @GetMapping("myinfo") // 내 정보 확인
    public UserInfoResponse memberInfo(HttpSession session){
            String id = SessionUtil.getLoginMemberId(session); //세션을 넣어 id 값을 가져오기
            if(id==null) id = SessionUtil.getLoginAdminId(session); // Admin 세션도 확인
            UserDTO memberInfo = userService.getUserInfo(id);
            return new UserInfoResponse(memberInfo); // UserDTO 에서 가져온 정보를 UserInfoResponse 에 담아 반환
    }




}
