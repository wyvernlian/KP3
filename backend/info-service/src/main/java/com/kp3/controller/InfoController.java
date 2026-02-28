package com.kp3.controller;

import com.kp3.entity.InfoCategory;
import com.kp3.entity.InfoInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoController {
    
    // 模拟资讯分类数据
    private static ArrayList<InfoCategory> categories = new ArrayList<>();
    // 模拟资讯数据
    private static ArrayList<InfoInfo> infos = new ArrayList<>();
    
    static {
        // 初始化资讯分类数据
        InfoCategory category1 = new InfoCategory();
        category1.setId(1L);
        category1.setCategoryName("科技新闻");
        category1.setCategoryCode("tech");
        category1.setSort(1);
        category1.setStatus(1);
        category1.setCreateTime(new Date());
        category1.setUpdateTime(new Date());
        categories.add(category1);
        
        InfoCategory category2 = new InfoCategory();
        category2.setId(2L);
        category2.setCategoryName("科普知识");
        category2.setCategoryCode("science");
        category2.setSort(2);
        category2.setStatus(1);
        category2.setCreateTime(new Date());
        category2.setUpdateTime(new Date());
        categories.add(category2);
        
        // 初始化资讯数据
        InfoInfo info1 = new InfoInfo();
        info1.setId(1L);
        info1.setTitle("人工智能的发展历程");
        info1.setCategoryId(1L);
        info1.setCategoryName("科技新闻");
        info1.setContent("人工智能是当今最热门的技术领域之一，本文将介绍其发展历程...");
        info1.setCoverImage("https://example.com/info1.jpg");
        info1.setVideoUrl("https://example.com/video1.mp4");
        info1.setViewCount(1000);
        info1.setLikeCount(100);
        info1.setStatus(1);
        info1.setCreateTime(new Date());
        info1.setUpdateTime(new Date());
        infos.add(info1);
        
        InfoInfo info2 = new InfoInfo();
        info2.setId(2L);
        info2.setTitle("黑洞的奥秘");
        info2.setCategoryId(2L);
        info2.setCategoryName("科普知识");
        info2.setContent("黑洞是宇宙中最神秘的天体之一，本文将为您揭开其神秘面纱...");
        info2.setCoverImage("https://example.com/info2.jpg");
        info2.setVideoUrl("https://example.com/video2.mp4");
        info2.setViewCount(800);
        info2.setLikeCount(80);
        info2.setStatus(1);
        info2.setCreateTime(new Date());
        info2.setUpdateTime(new Date());
        infos.add(info2);
        
        InfoInfo info3 = new InfoInfo();
        info3.setId(3L);
        info3.setTitle("量子计算的未来");
        info3.setCategoryId(1L);
        info3.setCategoryName("科技新闻");
        info3.setContent("量子计算将彻底改变我们的计算方式，本文将探讨其未来发展...");
        info3.setCoverImage("https://example.com/info3.jpg");
        info3.setVideoUrl("https://example.com/video3.mp4");
        info3.setViewCount(1200);
        info3.setLikeCount(120);
        info3.setStatus(1);
        info3.setCreateTime(new Date());
        info3.setUpdateTime(new Date());
        infos.add(info3);
    }
    
    // 资讯分类相关接口
    @GetMapping("/category/list")
    public Map<String, Object> getCategoryList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", categories);
        result.put("total", categories.size());
        return result;
    }
    
    // 资讯相关接口
    @GetMapping("/list")
    public Map<String, Object> getInfoList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", infos);
        result.put("total", infos.size());
        return result;
    }
    
    @GetMapping("/detail/{id}")
    public Map<String, Object> getInfoDetail(@PathVariable Long id) {
        for (InfoInfo info : infos) {
            if (info.getId().equals(id)) {
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("data", info);
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "资讯不存在");
        return result;
    }
    
    @PostMapping("/view")
    public Map<String, Object> recordView(@RequestBody Map<String, Long> data) {
        Long infoId = data.get("infoId");
        for (InfoInfo info : infos) {
            if (info.getId().equals(infoId)) {
                info.setViewCount(info.getViewCount() + 1);
                info.setUpdateTime(new Date());
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "浏览记录成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "资讯不存在");
        return result;
    }
    
    @PostMapping("/like")
    public Map<String, Object> recordLike(@RequestBody Map<String, Long> data) {
        Long infoId = data.get("infoId");
        for (InfoInfo info : infos) {
            if (info.getId().equals(infoId)) {
                info.setLikeCount(info.getLikeCount() + 1);
                info.setUpdateTime(new Date());
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "点赞成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "资讯不存在");
        return result;
    }
}