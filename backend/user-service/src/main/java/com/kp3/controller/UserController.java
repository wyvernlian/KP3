package com.kp3.controller;

import com.kp3.entity.SysUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    // 模拟用户数据
    private static ArrayList<SysUser> users = new ArrayList<>();
    
    static {
        SysUser user1 = new SysUser();
        user1.setId(1L);
        user1.setUsername("admin");
        user1.setName("管理员");
        user1.setPhone("13800138000");
        user1.setEmail("admin@example.com");
        user1.setStatus(1);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        users.add(user1);
        
        SysUser user2 = new SysUser();
        user2.setId(2L);
        user2.setUsername("user1");
        user2.setName("用户1");
        user2.setPhone("13800138001");
        user2.setEmail("user1@example.com");
        user2.setStatus(1);
        user2.setCreateTime(new Date());
        user2.setUpdateTime(new Date());
        users.add(user2);
    }
    
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", users);
        result.put("total", users.size());
        return result;
    }
    
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody SysUser user) {
        user.setId((long) (users.size() + 1));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        users.add(user);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "添加成功");
        return result;
    }
    
    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody SysUser user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                user.setUpdateTime(new Date());
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "更新成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "用户不存在");
        return result;
    }
    
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("message", "删除成功");
                return result;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 404);
        result.put("message", "用户不存在");
        return result;
    }
}