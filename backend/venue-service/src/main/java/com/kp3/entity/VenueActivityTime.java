package com.kp3.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "venue_activity_time")
public class VenueActivityTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long activityId;
    private String timeSlot; // 时间段，如 "09:00-11:00"
    private Integer capacity; // 名额
    private Integer remaining; // 剩余名额
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}