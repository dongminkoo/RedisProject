package com.personal.redisProject.service;

import com.personal.redisProject.dto.CategoryDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personal.redisProject.mapper.CategoryMapper;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper; //필드 주입

    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if (accountId != null) {
            categoryMapper.register(categoryDTO);
        }else{
            log.error("register Error: {}",categoryDTO);
            throw new RuntimeException("register Error! 게시글 등록 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if(categoryDTO != null) {
            categoryMapper.updateCategory(categoryDTO);
        }else{
            log.error("update Error: {}",categoryDTO);
            throw new RuntimeException("update Error! 게시글 수정 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }

    }

    @Override
    public void delete(int categoryId) {
        if(categoryId != 0) {
            categoryMapper.deleteCategory(categoryId);
        }else{
            log.error("delete Error: {}",categoryId);
            throw new RuntimeException("delete Error! 게시글 삭제 메서드를 확인해주세요\n" + "Params : " + categoryId);
        }
    }
}
