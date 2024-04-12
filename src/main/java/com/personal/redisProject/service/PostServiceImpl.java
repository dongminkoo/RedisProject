package com.personal.redisProject.service;

import com.personal.redisProject.dto.CommentDTO;
import com.personal.redisProject.dto.PostDTO;
import com.personal.redisProject.dto.TagDTO;
import com.personal.redisProject.dto.UserDTO;
import com.personal.redisProject.mapper.CommentMapper;
import com.personal.redisProject.mapper.PostMapper;
import com.personal.redisProject.mapper.TagMapper;
import com.personal.redisProject.mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService{

    @Autowired
    private PostMapper postMapper; //필드 주입

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TagMapper tagMapper;

    @CacheEvict(value = "post", allEntries = true)
    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberInfo.getId());
        postDTO.setCreateTime(new Date());

        if(memberInfo != null){
            postMapper.register(postDTO);
            Integer postId = postDTO.getId();
            for(int i=0; i<postDTO.getTagDTOList().size();i++){
                TagDTO tagDTO = postDTO.getTagDTOList().get(i);
                tagMapper.registerTag(tagDTO);
                Integer tagId = tagDTO.getId();
                tagMapper.createPostTag(tagId, postId);
            }
        }else{
            log.error("register Error: {}",postDTO);
            throw new RuntimeException("register Error! 게시글 등록 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }
    }

    @Override
    public List<PostDTO> getMyPosts(int accountId) {
        List<PostDTO> postDTOList = postMapper.selectMyPosts(accountId);

        return postDTOList;
    }

    @Override
    public void updatePosts(PostDTO postDTO) {
        if(postDTO != null && postDTO.getId() != 0){
            postMapper.updatePosts(postDTO);
        }else{
            log.error("update Error: {}",postDTO);
            throw new RuntimeException("update Error! 게시글 수정 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }
    }

    @Override
    public void deletePosts(int userId, int postId) {
        if (userId != 0 && postId != 0) {
            postMapper.deletePosts(postId);
        } else {
            log.error("delete Error: {}", postId);
            throw new RuntimeException("delete Error! 게시글 삭제 메서드를 확인해주세요\n" + "Params : " + postId);
        }
    }

    /* Tag 등록 */
    @Override
    public void registerComment(CommentDTO commentDTO) { //댓글 등록
        if(commentDTO.getPostId() != 0){
            commentMapper.registerComment(commentDTO);
        }else{
            log.error("registerComment error {}",commentDTO);
            throw new RuntimeException("registerComment Error! 댓글 등록 메서드를 확인해주세요\n" + "Params : " + commentDTO);
        }
    }

    @Override
    public void updateComment(CommentDTO commentDTO) { //댓글 수정
        if(commentDTO != null){
            commentMapper.updateComment(commentDTO);
        }else{
            log.error("updateComment error {}");
            throw new RuntimeException("updateComment Error! 댓글 등록 메서드를 확인해주세요\n");
        }
    }

    @Override
    public void deleteComment(int userId, int commentId) { //댓글 삭제
        if(userId != 0 && commentId != 0) {
            commentMapper.deleteComment(commentId);
        }else{
            log.error("deleteComment error {}",commentId);
            throw new RuntimeException("deleteComment Error! 댓글 삭제 메서드를 확인해주세요\n" + "Params : " + commentId);
        }
    }

    @Override
    public void registerTag(TagDTO tagDTO) { //태그 등록
        if(tagDTO != null) {
            tagMapper.registerTag(tagDTO);
        }else{
            log.error("registerTag error {}",tagDTO);
            throw new RuntimeException("registerTag Error! 태그 등록 메서드를 확인해주세요\n" + "Params : " + tagDTO);
        }
    }

    @Override
    public void updateTag(TagDTO tagDTO) {
        if(tagDTO != null){
            tagMapper.updateTag(tagDTO);
        }else{
            log.error("updateTag error {}");
            throw new RuntimeException("updateTag Error! 태그 수정 메서드를 확인해주세요\n");
        }
    }

    @Override
    public void deleteTag(int userId, int tagId) {
        if(userId != 0 && tagId != 0) {
            tagMapper.deleteTag(tagId);
        }else{
            log.error("deleteTag error {}");
            throw new RuntimeException("deleteTag Error! 태그 삭제 메서드를 확인해주세요");
        }
    }
}
