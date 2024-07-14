# 社团管理系统(数字百团)
## 项目介绍
我们的项目是社团管理系统(数字百团)，我们摒弃上一组的模板，从零开始分析需求，开发，测试，迭代，产出...
社团管理系统将用户端分为学生端，社团管理端，学院管理端，校团委管理端
主要的服务有：社团服务、学生服务、管理员服务、活动服务、安全认证服务、文件服务、网关服务、监控服务。
社团管理系统旨在成为一个信息共享以及简化业务流程的平台，它将填写文件，提交文件简化为一个按钮提交，系统会根据用户以往的经历填写数据；
在审单上，系统也简化了繁琐的整理文件流程，保留历史记录，一键提交审单，方便快捷。

## 小组信息
组长：
- 蔡星杰

组员：   
- 祁颖峰
- 董馨怡
- 朱彪
- 刘松凌


# 项目运行
This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

## Backend Setup

Before starting the backend configuration, make sure you have completed the following preparations:

- Install IntelliJ IDEA and configure JDK 17 environment.
- Ensure that Maven is installed and configured correctly.

Here are the steps to configure the database and email for each service:

### Database Configuration

1. Open IntelliJ IDEA and import your Maven project.

2. In the project structure, locate the configuration file for each service module.

3. For database configuration, find the configuration file for each service module (`backend/xxx-server/src/main/resources/application.yml`).

4. In the configuration file, locate the `datasource` section and modify the property values as shown in the example below:

   ```yaml
   datasource:
     url: jdbc:mysql://localhost:3306/your_database_name?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
     username: your_username
     password: your_password
     driver-class-name: com.mysql.cj.jdbc.Driver
   ```

   Replace `your_database_name` with your database name, `your_username` with your username, and `your_password` with your password.



### Email Configuration

1. Locate the configuration file for auth-server module(`backend/auth-server/src/main/resources/application.yml`).

2. In the configuration file, find the `mail` section and modify the property values as shown in the example below:

   ```yaml
   mail:
     # SMTP server host
     host: smtp.163.com
     # Port number
     port: 465
     # Service protocol
     protocol: smtp
     # Default encoding
     default-encoding: UTF-8
     # Email account for sending
     username: replace_with_your_account
     # Authorization code
     password: replace_with_your_password
   ```

   Replace `smtp.163.com` with your SMTP server host, `replace_with_your_account` with your email account for sending, and `replace_with_your_password` with your authorization code.

3. [How to get your Email Authorization code](https://blog.csdn.net/kissradish/article/details/108447972)
