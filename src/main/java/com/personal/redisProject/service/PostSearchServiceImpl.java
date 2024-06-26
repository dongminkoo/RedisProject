package com.personal.redisProject.service;

import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.request.PostSearchRequest;
import com.personal.redisProject.mapper.PostSearchMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PostSearchServiceImpl implements PostSearchService{

    @Autowired
    private PostSearchMapper postSearchMapper;

    @Cacheable(value = "getPosts", key="'getPosts' + #postSearchRequest.getName() + #postSearchRequest.getCategoryId()")
    @Override
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = null;
        try{
            postDTOList = postSearchMapper.selectPosts(postSearchRequest);
        }catch (RuntimeException e){
            log.error("selectPosts 메서드 실패: " + e.getMessage());
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostByTag(String tagName) {
        List<PostDTO> postDTOList = null;
        try{
            postDTOList = postSearchMapper.selectPostsByTag(tagName);
        }catch (RuntimeException e){
            log.error("selectPostsByTag 메서드 실패: " + e.getMessage());
        }
        return postDTOList;
    }


}
