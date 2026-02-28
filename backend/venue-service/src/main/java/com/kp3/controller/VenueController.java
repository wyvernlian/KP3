package com.kp3.controller;

import com.kp3.entity.VenueVenue;
import com.kp3.entity.VenueActivity;
import com.kp3.entity.VenueActivityCategory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/venue")
public class VenueController {
    
    // 模拟场馆数据
    private static ArrayList<VenueVenue> venues = new ArrayList<>();
    // 模拟活动分类数据
    private static ArrayList<VenueActivityCategory> activityCategories = new ArrayList<>();
    // 模拟活动数据
    private static ArrayList<VenueActivity> activities = new ArrayList<>();
    
    static {
        // 初始化场馆数据
        VenueVenue venue1 = new VenueVenue();
        venue1.setId(1L);
        venue1.setVenueName("科普科技馆");
        venue1.setAddress("北京市海淀区中关村南大街5号");
        venue1.setDescription("一座现代化的科普科技馆，展示各种科学知识和科技成果");
        venue1.setImageUrl("https://example.com/venue1.jpg");
        venue1.setThreeDUrl("https://example.com/3d/venue1");
        venue1.setStatus(1);
        venue1.setCreateTime(new Date());
        venue1.setUpdateTime(new Date());
        venues.add(venue1);
        
        // 初始化活动分类数据
        VenueActivityCategory category1 = new VenueActivityCategory();
        category1.setId(1L);
        category1.setCategoryName("参观预约");
        category1.setCategoryCode("visit");
        category1.setSort(1);
        category1.setStatus(1);
        category1.setShowInHome(1);
        category1.setCreateTime(new Date());
        category1.setUpdateTime(new Date());
        activityCategories.add(category1);
        
        VenueActivityCategory category2 = new VenueActivityCategory();
        category2.setId(2L);
        category2.setCategoryName("科普活动");
        category2.setCategoryCode("science");
        category2.setSort(2);
        category2.setStatus(1);
        category2.setShowInHome(1);
        category2.setCreateTime(new Date());
        category2.setUpdateTime(new Date());
        activityCategories.add(category2);
        
        VenueActivityCategory category3 = new VenueActivityCategory();
        category3.setId(3L);
        category3.setCategoryName("科普研学");
        category3.setCategoryCode("research");
        category3.setSort(3);
        category3.setStatus(1);
        category3.setShowInHome(1);
        category3.setCreateTime(new Date());
        category3.setUpdateTime(new Date());
        activityCategories.add(category3);
        
        // 初始化活动数据
        VenueActivity activity1 = new VenueActivity();
        activity1.setId(1L);
        activity1.setActivityName("参观科技馆");
        activity1.setCategoryId(1L);
        activity1.setCategoryName("参观预约");
        activity1.setType("参观预约");
        activity1.setTimeType("选日期");
        activity1.setStartDate("2026-03-01");
        activity1.setEndDate("2026-03-31");
        activity1.setDescription("参观科技馆的常设展览，了解科学知识");
        activity1.setNotice("请携带身份证，按时到达");
        activity1.setMaxPeople(5);
        activity1.setStatus(1);
        activity1.setCreateTime(new Date());
        activity1.setUpdateTime(new Date());
        activities.add(activity1);
        
        VenueActivity activity2 = new VenueActivity();
        activity2.setId(2L);
        activity2.setActivityName("科普实验");
        activity2.setCategoryId(2L);
        activity2.setCategoryName("科普活动");
        activity2.setType("科普活动");
        activity2.setTimeType("按排期");
        activity2.setStartDate("2026-03-01");
        activity2.setEndDate("2026-03-31");
        activity2.setDescription("参与有趣的科普实验，动手实践科学原理");
        activity2.setNotice("请穿着舒适的衣服，服从工作人员安排");
        activity2.setMaxPeople(10);
        activity2.setStatus(1);
        activity2.setCreateTime(new Date());
        activity2.setUpdateTime(new Date());
        activities.add(activity2);
    }
    
    // 场馆相关接口
    @GetMapping("/list")
    public Map<String, Object> getVenueList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", venues);
        result.put("total", venues.size());
        return result;
    }
    
    @PostMapping("/add")
    public Map<String, Object> addVenue(@RequestBody VenueVenue venue) {
        venue.setId((long) (venues.size() + 1));
        venue.setCreateTime(new Date());
        venue.setUpdateTime(new Date());
        venues.add(venue);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "添加成功");
        return result;
    }
    
    // 活动分类相关接口
    @GetMapping("/category/list")
    public Map<String, Object> getActivityCategoryList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", activityCategories);
        result.put("total", activityCategories.size());
        return result;
    }
    
    // 活动相关接口
    @GetMapping("/activity/list")
    public Map<String, Object> getActivityList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", activities);
        result.put("total", activities.size());
        return result;
    }
    
    @PostMapping("/activity/add")
    public Map<String, Object> addActivity(@RequestBody VenueActivity activity) {
        activity.setId((long) (activities.size() + 1));
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        activities.add(activity);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "添加成功");
        return result;
    }
}