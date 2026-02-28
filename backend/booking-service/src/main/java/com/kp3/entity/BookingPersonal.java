package com.kp3.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking_personal")
public class BookingPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long activityId;
    private String activityName;
    private String bookingDate;
    private String bookingTime;
    private Integer numberOfPeople;
    private String name;
    private String idCard;
    private String phone;
    private String additionalInfo;
    private Integer status; // 0: 待核销, 1: 已核销, 2: 已取消
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}