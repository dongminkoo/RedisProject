package com.personal.redisProject.controller;

import com.personal.redisProject.aop.LoginCheck;
import com.personal.redisProject.dto.CategoryDTO;
import com.personal.redisProject.service.CategoryServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/categories")
@Log4j2
public class CategoryController {

    private CategoryServiceImpl categoryService; //생성자 주입

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN) //Login AOP => ADMIN 일 경우만
    public void registerCategory(String accountId, @RequestBody CategoryDTO categoryDTO){
        categoryService.register(accountId, categoryDTO);
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN) //Login AOP => ADMIN 일 경우만
    public void updateCategories(String accountId, @PathVariable(name = "categoryId") int categoryId, @RequestBody CategoryRequest categoryRequest){
       CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryRequest.getName(), CategoryDTO.SortStatus.NEWEST, 10, 1);
       categoryService.update(categoryDTO);
    }


    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN) //Login AOP => ADMIN 일 경우만
    public void deleteCategories(String accountId, @PathVariable(name = "categoryId") int categoryId){
        categoryService.delete(categoryId);
    }


    //-------------- request 객체 ------------------------
    //----inner class는 캡슐화가 되어 외부 클래스에서 접근 불가 ---
    @Getter
    @Setter
    private static class CategoryRequest {
        private int id;
        private String name;
    }




}
