package com.personal.redisProject.mapper;

import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {
    public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
}
