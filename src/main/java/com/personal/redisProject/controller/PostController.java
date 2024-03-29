package com.personal.redisProject.controller;

import com.personal.redisProject.aop.LoginCheck;
import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.UserDTO;
import com.personal.redisProject.dto.response.CommonResponse;
import com.personal.redisProject.service.PostServiceImpl;
import com.personal.redisProject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    public PostController(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountId, @RequestBody PostDTO postDTO){
        postService.register(accountId, postDTO);
        CommonResponse  commonResponse = new CommonResponse(HttpStatus.OK, "Success", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDTO>>> myPostInfo(String accountId){
        UserDTO memberInfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyPosts(memberInfo.getId());
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "Success", "myPostInfo", postDTOList);
        return ResponseEntity.ok(commonResponse);
    }

//    @PatchMapping("{postId}")
//    @LoginCheck(type = LoginCheck.UserType.USER)
//    public ResponseEntity<CommonResponse<PostResponse>> updatePosts(String accountId, @PathVariable(name = "postId") int postId, @RequestBody PostRequest postRequest){
//        UserDTO memberInfo = userService.getUserInfo(accountId);
//
//
//    }

    // -------------- response 객체 --------------
    @Getter
    @AllArgsConstructor
    private static class PostResponse{
        private List<PostDTO>postDTO;
    }

    //-------------- request 객체 --------------
    @Setter
    @Getter
    private static class PostRequest{
        private String name;
        private String contents;
        private int views;
        private int categoryId;
        private int userId;
        private int fileId;
        private Date updateTime;
    }
}
