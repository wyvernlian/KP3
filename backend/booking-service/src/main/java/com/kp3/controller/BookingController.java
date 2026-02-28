package com.kp3.controller;

import com.kp3.entity.BookingPersonal;
import com.kp3.entity.BookingTeam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    // 模拟个人预约数据
    private static ArrayList<BookingPersonal> personalBookings = new ArrayList<>();
    // 模拟团队预约数据
    private static ArrayList<BookingTeam> teamBookings = new ArrayList<>();
    
    static {
        BookingPersonal booking1 = new BookingPersonal();
        booking1.setId(1L);
        booking1.setUserId(1L);
        booking1.setActivityId(1L);
        booking1.setActivityName("参观科技馆");
        booking1.setBookingDate("2026-03-01");
        booking1.setBookingTime("09:00-11:00");
        booking1.setNumberOfPeople(2);
        booking1.setName("张三");
        booking1.setIdCard("110101199001011234");
        booking1.setPhone("13800138000");
        booking1.setStatus(0);
        booking1.setCreateTime(new Date());
        booking1.setUpdateTime(new Date());
        personalBookings.add(booking1);
        
        BookingTeam booking2 = new BookingTeam();
        booking2.setId(1L);
        booking2.setUserId(2L);
        booking2.setActivityId(1L);
        booking2.setActivityName("参观科技馆");
        booking2.setBookingDate("2026-03-02");
        booking2.setBookingTime("14:00-16:00");
        booking2.setTeamType("学校");
        booking2.setTeamName("XX小学");
        booking2.setNumberOfPeople(50);
        booking2.setContactName("李老师");
        booking2.setContactPhone("13900139000");
        booking2.setStatus(0);
        booking2.setCreateTime(new Date());
        booking2.setUpdateTime(new Date());
        teamBookings.add(booking2);
    }
    
    // 个人预约相关接口
    @GetMapping("/personal/list")
    public Map<String, Object> getPersonalBookingList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", personalBookings);
        result.put("total", personalBookings.size());
        return result;
    }
    
    @PostMapping("/personal/add")
    public Map<String, Object> addPersonalBooking(@RequestBody BookingPersonal booking) {
        booking.setId((long) (personalBookings.size() + 1));
        booking.setCreateTime(new Date());
        booking.setUpdateTime(new Date());
        booking.setStatus(0);
        personalBookings.add(booking);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "预约成功");
        return result;
    }
    
    @PutMapping("/personal/cancel/{id}")
    public Map<String, Object> cancelPersonalBooking(@PathVariable Long id) {
        for (BookingPersonal booking : personalBookings) {
            if (booking.getId().equals(id)) {
                booking.setStatus(2);
                booking.setUpdateTime(new Date());
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "取消成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "预约不存在");
        return result;
    }
    
    // 团队预约相关接口
    @GetMapping("/team/list")
    public Map<String, Object> getTeamBookingList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", teamBookings);
        result.put("total", teamBookings.size());
        return result;
    }
    
    @PostMapping("/team/add")
    public Map<String, Object> addTeamBooking(@RequestBody BookingTeam booking) {
        booking.setId((long) (teamBookings.size() + 1));
        booking.setCreateTime(new Date());
        booking.setUpdateTime(new Date());
        booking.setStatus(0);
        teamBookings.add(booking);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "预约成功");
        return result;
    }
    
    @PutMapping("/team/cancel/{id}")
    public Map<String, Object> cancelTeamBooking(@PathVariable Long id) {
        for (BookingTeam booking : teamBookings) {
            if (booking.getId().equals(id)) {
                booking.setStatus(3);
                booking.setUpdateTime(new Date());
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "取消成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "预约不存在");
        return result;
    }
}