# 科普科技馆预约平台API文档

## 1. 认证相关接口

### 1.1 用户登录
- **接口地址**：`/api/user/login`
- **请求方式**：POST
- **请求参数**：
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "登录成功",
    "data": {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
      "user": {
        "id": 1,
        "username": "admin",
        "name": "管理员",
        "phone": "13800138000",
        "email": "admin@example.com",
        "roles": ["admin"]
      }
    }
  }
  ```

### 1.2 获取用户信息
- **接口地址**：`/api/user/info`
- **请求方式**：GET
- **请求头**：`Authorization: Bearer {token}`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "username": "admin",
      "name": "管理员",
      "phone": "13800138000",
      "email": "admin@example.com",
      "roles": ["admin"]
    }
  }
  ```

## 2. 系统管理接口

### 2.1 用户管理
- **接口地址**：`/api/system/user`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - GET：`page=1&size=10&username=admin`
  - POST/PUT：
    ```json
    {
      "username": "user1",
      "password": "123456",
      "name": "用户1",
      "phone": "13800138001",
      "email": "user1@example.com",
      "status": 1,
      "orgId": 1
    }
    ```

### 2.2 角色管理
- **接口地址**：`/api/system/role`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "roleName": "运营人员",
      "roleCode": "operator",
      "description": "运营机构人员",
      "status": 1
    }
    ```

### 2.3 菜单管理
- **接口地址**：`/api/system/menu`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "menuName": "用户管理",
      "menuCode": "user",
      "url": "/system/user",
      "icon": "user",
      "parentId": 1,
      "sort": 1,
      "type": 1,
      "status": 1
    }
    ```

## 3. 运营机构接口

### 3.1 运营机构管理
- **接口地址**：`/api/organization`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "orgName": "科普科技馆",
      "address": "北京市海淀区中关村南大街5号",
      "contactName": "张三",
      "contactPhone": "13800138000",
      "status": 1
    }
    ```

### 3.2 运营机构用户管理
- **接口地址**：`/api/organization/user`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "username": "orguser1",
      "password": "123456",
      "name": "机构用户1",
      "orgId": 1,
      "phone": "13800138001",
      "status": 1
    }
    ```

## 4. 场馆和活动接口

### 4.1 场馆管理
- **接口地址**：`/api/venue/venue`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "venueName": "科普科技馆",
      "address": "北京市海淀区中关村南大街5号",
      "description": "一座现代化的科普科技馆",
      "imageUrl": "https://example.com/venue1.jpg",
      "threeDUrl": "https://example.com/3d/venue1",
      "status": 1
    }
    ```

### 4.2 活动分类管理
- **接口地址**：`/api/venue/activity-category`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "categoryName": "参观预约",
      "categoryCode": "visit",
      "sort": 1,
      "status": 1,
      "showInHome": 1
    }
    ```

### 4.3 活动管理
- **接口地址**：`/api/venue/activity`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "activityName": "参观科技馆",
      "categoryId": 1,
      "categoryName": "参观预约",
      "type": "参观预约",
      "timeType": "选日期",
      "startDate": "2026-03-01",
      "endDate": "2026-03-31",
      "description": "参观科技馆的常设展览",
      "notice": "请携带身份证，按时到达",
      "maxPeople": 5,
      "status": 1
    }
    ```

### 4.4 活动时间段管理
- **接口地址**：`/api/venue/activity-time`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "activityId": 1,
      "timeSlot": "09:00-11:00",
      "capacity": 50,
      "remaining": 50
    }
    ```

## 5. 预约管理接口

### 5.1 个人预约
- **接口地址**：`/api/booking/personal`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST：
    ```json
    {
      "activityId": 1,
      "activityName": "参观科技馆",
      "bookingDate": "2026-03-01",
      "bookingTime": "09:00-11:00",
      "numberOfPeople": 2,
      "name": "张三",
      "idCard": "110101199001011234",
      "phone": "13800138000",
      "additionalInfo": "无"
    }
    ```

### 5.2 团队预约
- **接口地址**：`/api/booking/team`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST：
    ```json
    {
      "activityId": 1,
      "activityName": "参观科技馆",
      "bookingDate": "2026-03-01",
      "bookingTime": "09:00-11:00",
      "teamType": "学校",
      "teamName": "XX小学",
      "numberOfPeople": 50,
      "contactName": "李老师",
      "contactPhone": "13900139000",
      "remarks": "需要讲解服务",
      "attachment": "https://example.com/attachment.pdf"
    }
    ```

### 5.3 核销和到访登记
- **接口地址**：`/api/booking/verify`（核销）、`/api/booking/visit`（到访登记）
- **请求方式**：POST
- **请求参数**：
  - 核销：
    ```json
    {
      "bookingId": 1,
      "bookingType": "personal",
      "name": "张三",
      "phone": "13800138000",
      "operator": "管理员"
    }
    ```
  - 到访登记：
    ```json
    {
      "name": "李四",
      "idCard": "110101199001011235",
      "phone": "13900139000",
      "visitDate": "2026-03-01",
      "visitTime": "09:00-11:00",
      "operator": "管理员"
    }
    ```

## 6. 科普资讯接口

### 6.1 资讯分类管理
- **接口地址**：`/api/info/category`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "categoryName": "科技新闻",
      "categoryCode": "tech",
      "sort": 1,
      "status": 1
    }
    ```

### 6.2 资讯管理
- **接口地址**：`/api/info/info`
- **请求方式**：GET（获取列表）、POST（新增）、PUT（修改）、DELETE（删除）
- **请求参数**：
  - POST/PUT：
    ```json
    {
      "title": "人工智能的发展历程",
      "categoryId": 1,
      "categoryName": "科技新闻",
      "content": "人工智能（Artificial Intelligence，简称AI）是研究、开发用于模拟、延伸和扩展人的智能的理论、方法、技术及应用系统的一门新的技术科学。",
      "coverImage": "https://example.com/info1.jpg",
      "videoUrl": "https://example.com/video1.mp4",
      "status": 1
    }
    ```

### 6.3 资讯浏览和点赞
- **接口地址**：`/api/info/view`（浏览）、`/api/info/like`（点赞）
- **请求方式**：POST
- **请求参数**：
  - 浏览：
    ```json
    {
      "infoId": 1,
      "userId": 1
    }
    ```
  - 点赞：
    ```json
    {
      "infoId": 1,
      "userId": 1
    }
    ```

## 7. 数据分析接口

### 7.1 流量统计
- **接口地址**：`/api/analysis/traffic`
- **请求方式**：GET
- **请求参数**：`startDate=2026-02-27&endDate=2026-03-27`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {"date": "2026-02-27", "count": 100},
      {"date": "2026-02-28", "count": 120}
    ]
  }
  ```

### 7.2 内容访问流量统计
- **接口地址**：`/api/analysis/content`
- **请求方式**：GET
- **请求参数**：`type=info&startDate=2026-02-27&endDate=2026-03-27`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {"id": 1, "title": "人工智能的发展历程", "viewCount": 1000},
      {"id": 2, "title": "黑洞的奥秘", "viewCount": 800}
    ]
  }
  ```

### 7.3 场馆数据看板
- **接口地址**：`/api/analysis/venue`
- **请求方式**：GET
- **请求参数**：`venueId=1&startDate=2026-02-27&endDate=2026-03-27`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "totalVisits": 1000,
      "totalBookings": 500,
      "dailyData": [
        {"date": "2026-02-27", "visits": 50, "bookings": 30},
        {"date": "2026-02-28", "visits": 60, "bookings": 35}
      ]
    }
  }
  ```

## 8. 微信小程序接口

### 8.1 注册登录
- **接口地址**：`/api/wechat/login`
- **请求方式**：POST
- **请求参数**：
  ```json
  {
    "phone": "13800138000",
    "code": "123456",
    "wechatOpenId": "oABC123456"
  }
  ```

### 8.2 获取首页数据
- **接口地址**：`/api/wechat/home`
- **请求方式**：GET
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "carousel": ["https://example.com/carousel1.jpg"],
      "activities": [
        {"id": 1, "name": "参观科技馆", "image": "https://example.com/activity1.jpg"}
      ],
      "infos": [
        {"id": 1, "title": "人工智能的发展历程", "coverImage": "https://example.com/info1.jpg"}
      ]
    }
  }
  ```

### 8.3 预约相关接口
- **接口地址**：`/api/wechat/booking/personal`（个人预约）、`/api/wechat/booking/team`（团队预约）
- **请求方式**：POST
- **请求参数**：与PC端相同

### 8.4 场馆和资讯接口
- **接口地址**：`/api/wechat/venue`（场馆列表）、`/api/wechat/info`（资讯列表）
- **请求方式**：GET
- **请求参数**：`page=1&size=10&categoryId=1`
