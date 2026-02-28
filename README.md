# 科普科技馆预约平台

## 项目简介

科普科技馆预约平台是一个集PC端后台管理系统和微信科普预约小程序于一体的预约管理系统。该平台提供了用户认证、系统管理、运营机构管理、场馆和活动管理、预约管理、科普资讯管理、数据分析等功能，旨在为科普科技馆提供便捷的预约服务。

## 技术栈

- **后端**：Java 1.8、Spring Boot 2.5.6、Spring Cloud 2020.0.5、Nacos 2.0.3、MySQL 8.0、Redis 7.0、MinIO
- **前端（PC）**：Vue 3、Element Plus、Vue Router、Pinia、Axios
- **前端（微信小程序）**：Vue 3、Vant、Vue Router、Pinia、Axios

## 项目结构

```
KP3/
├── backend/                 # 后端服务
│   ├── gateway/             # API网关
│   ├── user-service/        # 用户服务
│   ├── booking-service/     # 预约服务
│   ├── venue-service/       # 场馆和活动服务
│   ├── info-service/        # 科普资讯服务
│   └── stat-service/        # 数据分析服务
├── frontend/                # 前端项目
│   ├── admin/               # PC端后台管理系统
│   └── miniprogram/         # 微信小程序
├── docker-compose.yml       # Docker Compose配置
├── init.sql                 # 数据库初始化脚本
├── api-doc.md               # API文档
└── README.md                # 项目说明
```

## 快速开始

### 1. 环境准备

- JDK 1.8+
- Maven 3.6+
- Node.js 14+
- Docker

### 2. 启动基础服务

使用Docker Compose启动MySQL、Redis、MinIO和Nacos服务：

```bash
docker-compose up -d
```

### 3. 初始化数据库

连接MySQL数据库（用户名：root，密码：root），执行`init.sql`脚本初始化数据库结构和数据。

### 4. 构建后端服务

```bash
# 进入backend目录
cd backend

# 构建所有服务
mvn clean install

# 启动各个服务（可使用IDE或命令行）
# 1. 启动Nacos服务（如果未使用Docker Compose启动）
# 2. 启动gateway服务
# 3. 启动user-service服务
# 4. 启动booking-service服务
# 5. 启动venue-service服务
# 6. 启动info-service服务
# 7. 启动stat-service服务
```

### 5. 构建前端项目

#### 5.1 PC端后台管理系统

```bash
# 进入frontend/admin目录
cd frontend/admin

# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build
```

#### 5.2 微信小程序

```bash
# 进入frontend/miniprogram目录
cd frontend/miniprogram

# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build
```

## 访问方式

### PC端后台管理系统
- 访问地址：http://localhost:5173
- 用户名：admin
- 密码：123456

### 微信小程序
- 使用微信开发者工具导入`frontend/miniprogram`目录
- 配置小程序AppID
- 运行小程序

## 功能模块

### PC端后台管理系统
1. **用户登录**：系统登录功能
2. **系统管理**：用户管理、功能菜单管理、用户角色和权限管理、平台设置、数据字典、操作日志
3. **运营机构**：运营机构管理、运营机构用户管理、预约用户管理、预约团队管理
4. **场馆和活动**：场馆管理、活动分类、活动管理
5. **预约管理**：个人预约、团队预约、核销和到访登记
6. **科普资讯**：资讯分类、资讯管理
7. **数据分析**：流量统计、内容访问流量统计、场馆数据看板

### 微信小程序
1. **注册登录**：绑定手机号进行实名制认证，支持微信授权登录
2. **首页**：轮播图、目录（线上场馆、科普活动、个人预约、团队预约）、科普研学、科普资讯
3. **个人预约**：选择可预约日期、时段、人数，填写预约人员信息
4. **团队预约**：选择可预约日期、时段、团队类型、团队名称、预约人数、联系人信息
5. **活动预约**：活动详情、选择可预约日期、时段、填写预约人员信息
6. **线上场馆**：场馆列表、场馆详情（包括3D图）
7. **科普资讯**：查询、资讯分类、资讯列表、资讯详情
8. **个人中心**：个人信息、个人预约历史、团队预约历史、亲友信息

## API文档

详细的API接口说明请参考`api-doc.md`文件。

## 注意事项

1. 确保Docker服务正常运行
2. 确保数据库初始化脚本执行成功
3. 确保所有服务启动顺序正确（Nacos → 网关 → 其他服务）
4. 微信小程序需要配置AppID和相关权限
5. 生产环境需要配置HTTPS和相关安全措施

## 联系方式

如有问题，请联系：
- 邮箱：admin@example.com
- 电话：13800138000
