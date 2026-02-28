package com.kp3.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "info_info")
public class InfoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private Long categoryId;
    private String categoryName;
    private String content;
    private String coverImage;
    private String videoUrl;
    private Integer viewCount;
    private Integer likeCount;
    private Integer status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}