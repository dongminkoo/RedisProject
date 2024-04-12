package com.personal.redisProject.service;

import com.personal.redisProject.dto.CommentDTO;
import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.TagDTO;

import java.util.List;

public interface PostService {

    void register(String id, PostDTO postDTO); //게시글 등록
    List<PostDTO> getMyPosts(int accountId); //내 게시글 조회
    void updatePosts(PostDTO postDTO); //게시글 수정
    void deletePosts(int userId, int postId); //게시글 삭제
    void registerComment(CommentDTO commentDTO); //댓글 등록
    void updateComment(CommentDTO commentDTO); //댓글 수정
    void deleteComment(int userId, int commentId); //댓글 삭제
    void registerTag(TagDTO tagDTO); //태그 등록
    void updateTag(TagDTO tagDTO); //태그 수정
    void deleteTag(int userId,int tagId); //태그 삭제
}
