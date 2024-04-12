package com.personal.redisProject.mapper;

import com.personal.redisProject.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public int registerComment(CommentDTO commentDTO);
    public void updateComment(CommentDTO commentDTO);
    public void deleteComment(int commentId);

}
