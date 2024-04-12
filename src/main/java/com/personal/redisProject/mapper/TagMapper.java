package com.personal.redisProject.mapper;


import com.personal.redisProject.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {
    public int registerTag(TagDTO tagDTO);
    public void updateTag(TagDTO tagDTO);
    public void deleteTag(int tagId);
    public void createPostTag(Integer tagId, Integer postId);
}
