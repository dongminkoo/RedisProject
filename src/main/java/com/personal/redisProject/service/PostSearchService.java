package com.personal.redisProject.service;

import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> getPosts(PostSearchRequest postSearchRequest);
}
