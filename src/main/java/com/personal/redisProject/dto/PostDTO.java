package com.personal.redisProject.dto;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int id; //게시글 id
    private String name; //게시글 이름
    private int isAdmin;
    private String contents; //게시글 본문값
    private Date createTime; //게시글 생성 날짜
    private int views; //조회수
    private int categoryId; //게시글 카테고리 번호
    private int userId; //게시글 userid 값
    private int fileId;
    private Date updateTime; //게시글 수정날짜
}
