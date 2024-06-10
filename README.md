# Digital100MassOrg

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

1. Locate the configuration file for auth-server module(`backend/xxx-server/src/main/resources/application.yml`).

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
