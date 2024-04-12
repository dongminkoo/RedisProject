package com.personal.redisProject.service;

import com.personal.redisProject.dto.CategoryDTO;
import com.personal.redisProject.dto.CommentDTO;
import com.personal.redisProject.dto.TagDTO;

public interface CategoryService {

    void register(String accountId, CategoryDTO categoryDTO); //게시글 등록
    void update(CategoryDTO categoryDTO); //게시글 수정
    void delete(int categoryId); //게시글 삭제
}
