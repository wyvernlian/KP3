package com.kp3.controller;

import com.kp3.entity.SysUser;
import com.kp3.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        // 从 loginData 中获取用户名（后续可扩展用于数据库查询）
        loginData.get("username");
        String password = loginData.get("password");
        
        // 这里应该从数据库查询用户，这里简化处理
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setName("管理员");
        
        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 401);
            result.put("message", "用户名或密码错误");
            return result;
        }
        
        // 生成token
        String token = jwtUtils.generateToken(user.getId(), user.getUsername());
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("token", token);
        result.put("user", user);
        
        return result;
    }
    
    @PostMapping("/logout")
    public Map<String, Object> logout() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登出成功");
        return result;
    }
}