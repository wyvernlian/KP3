package com.kp3.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking_team")
public class BookingTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long activityId;
    private String activityName;
    private String bookingDate;
    private String bookingTime;
    private String teamType;
    private String teamName;
    private Integer numberOfPeople;
    private String contactName;
    private String contactPhone;
    private String remarks;
    private String attachment;
    private Integer status; // 0: 待审核, 1: 已通过, 2: 已拒绝, 3: 已取消
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}