package com.personal.redisProject.mapper;

import com.personal.redisProject.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostMapper {

    public int register(PostDTO postDTO);
    public List<PostDTO> selectMyPosts(int accountId);
    public void updatePosts(PostDTO postDTO);
    public void deletePosts(int postId);
}
