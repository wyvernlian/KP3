package com.kp3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stat")
public class StatController {
    
    // 流量统计接口
    @GetMapping("/traffic")
    public Map<String, Object> getTrafficStat() {
        // 模拟流量数据
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        
        Map<String, Object> day1 = new HashMap<>();
        day1.put("date", "2026-02-21");
        day1.put("pv", 1200);
        day1.put("uv", 800);
        data.add(day1);
        
        Map<String, Object> day2 = new HashMap<>();
        day2.put("date", "2026-02-22");
        day2.put("pv", 1500);
        day2.put("uv", 1000);
        data.add(day2);
        
        Map<String, Object> day3 = new HashMap<>();
        day3.put("date", "2026-02-23");
        day3.put("pv", 1800);
        day3.put("uv", 1200);
        data.add(day3);
        
        Map<String, Object> day4 = new HashMap<>();
        day4.put("date", "2026-02-24");
        day4.put("pv", 1600);
        day4.put("uv", 1100);
        data.add(day4);
        
        Map<String, Object> day5 = new HashMap<>();
        day5.put("date", "2026-02-25");
        day5.put("pv", 2000);
        day5.put("uv", 1400);
        data.add(day5);
        
        Map<String, Object> day6 = new HashMap<>();
        day6.put("date", "2026-02-26");
        day6.put("pv", 2200);
        day6.put("uv", 1500);
        data.add(day6);
        
        Map<String, Object> day7 = new HashMap<>();
        day7.put("date", "2026-02-27");
        day7.put("pv", 1900);
        day7.put("uv", 1300);
        data.add(day7);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", data);
        return result;
    }
    
    // 内容访问流量统计接口
    @GetMapping("/content")
    public Map<String, Object> getContentStat() {
        // 模拟内容访问数据
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        
        Map<String, Object> content1 = new HashMap<>();
        content1.put("id", 1);
        content1.put("title", "人工智能的发展历程");
        content1.put("viewCount", 1000);
        content1.put("likeCount", 100);
        data.add(content1);
        
        Map<String, Object> content2 = new HashMap<>();
        content2.put("id", 2);
        content2.put("title", "黑洞的奥秘");
        content2.put("viewCount", 800);
        content2.put("likeCount", 80);
        data.add(content2);
        
        Map<String, Object> content3 = new HashMap<>();
        content3.put("id", 3);
        content3.put("title", "量子计算的未来");
        content3.put("viewCount", 1200);
        content3.put("likeCount", 120);
        data.add(content3);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", data);
        return result;
    }
    
    // 场馆数据看板接口
    @GetMapping("/venue")
    public Map<String, Object> getVenueStat() {
        // 模拟场馆数据
        Map<String, Object> data = new HashMap<>();
        
        // 总预约数
        data.put("totalBookings", 1200);
        // 今日预约数
        data.put("todayBookings", 50);
        // 总访问量
        data.put("totalVisits", 10000);
        // 今日访问量
        data.put("todayVisits", 300);
        // 场馆利用率
        data.put("utilizationRate", "85%");
        // 热门活动
        ArrayList<Map<String, Object>> hotActivities = new ArrayList<>();
        
        Map<String, Object> activity1 = new HashMap<>();
        activity1.put("id", 1);
        activity1.put("name", "参观科技馆");
        activity1.put("bookingCount", 500);
        hotActivities.add(activity1);
        
        Map<String, Object> activity2 = new HashMap<>();
        activity2.put("id", 2);
        activity2.put("name", "科普实验");
        activity2.put("bookingCount", 300);
        hotActivities.add(activity2);
        
        data.put("hotActivities", hotActivities);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", data);
        return result;
    }
}