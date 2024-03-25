package com.personal.redisProject.service;

import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.UserDTO;
import com.personal.redisProject.mapper.PostMapper;
import com.personal.redisProject.mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService{

    @Autowired
    private PostMapper postMapper; //필드 주입

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberinfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberinfo.getId());
        postDTO.setCreateTime(new Date());

        if(memberinfo != null){
            postMapper.register(postDTO);
        }else{
            log.error("register Error: {}",postDTO);
            throw new RuntimeException("register Error! 게시글 등록 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }
    }

    @Override
    public List<PostDTO> getMyPosts(int accountId) {
        List<PostDTO> postDTOList = postMapper.selectMyPosts(accountId);

        return postDTOList;
    }

    @Override
    public void updatePosts(PostDTO postDTO) {
        if(postDTO != null && postDTO.getId() != 0){
            postMapper.updatePosts(postDTO);
        }else{
            log.error("update Error: {}",postDTO);
            throw new RuntimeException("update Error! 게시글 수정 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }
    }

    @Override
    public void deletePosts(int userId, int postId) {
        if (userId != 0 && postId != 0) {
            postMapper.deletePosts(postId);
        } else {
            log.error("delete Error: {}", postId);
            throw new RuntimeException("delete Error! 게시글 삭제 메서드를 확인해주세요\n" + "Params : " + postId);
        }
    }
}
