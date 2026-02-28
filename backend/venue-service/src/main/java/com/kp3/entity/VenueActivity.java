package com.kp3.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "venue_activity")
public class VenueActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String activityName;
    private Long categoryId;
    private String categoryName;
    private String type; // 参观预约、科普活动、科普研学
    private String timeType; // 按排期、选日期、无需指定日期
    private String startDate;
    private String endDate;
    private String description;
    private String notice;
    private Integer maxPeople; // 最多可约人数
    private Integer status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}