
#代码库说明

## 环境配置

###设置私有MVN仓库
配置/your_home/.m2/settings.xml如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <mirrors>
    <mirror>
        <id>aisino</id>
        <mirrorOf>*</mirrorOf>
        <name>Nexus Aisino</name>
        <url>http://172.16.1.204:38081/repository/maven-public/</url>
    </mirror>
    <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>*</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>
 </mirrors>

    <servers>      
        <server>
            <id>aisino</id>
            <username>admin</username>
            <password>Ask Xinming Lai for the password</password>
        </server>
    </servers>

<profiles>
    <profile>
      <id>nexus</id>
      <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
      </activation>
      
      <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
      <repositories>
        <repository>
          <id>central</id>
          <url>http://172.16.1.204:38081/repository/maven-central/</url>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
          </releases>
          <snapshots>
            <enabled>false</enabled>
            <updatePolicy>always</updatePolicy>
          </snapshots>
        </repository>
        <repository>
          <id>nexus-snapshots</id>
          <url>http://172.16.1.204:38081/repository/maven-snapshots/</url>
          <releases>
            <enabled>false</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
        <repository>
          <id>nexus-releases</id>
          <url>http://172.16.1.204:38081/repository/maven-releases/</url>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <activeProfiles>
    <activeProfile>nexus</activeProfile>
  </activeProfiles>

</settings>

```
### 安装JDK 和 Maven

## CheckOut代码库

**SVN地址**

```
http://172.16.6.16/svn/SocialInvoice/sourcecode/server/trunk/social-invoice/
```
**SVN Ignore设置**

```
# 切换到自己的项目根目录
cd social-invoice 

svn propset svn:ignore -R target/ .
svn propset svn:ignore -R .svn/ .
```


## 本地开发步骤

### 编译parent工程
**注：由于上面私有仓库已经配置，本步骤可忽略**

```
cd parent
mvn install
```
### 编译ms-common工程
**注：由于上面私有仓库已经配置，本步骤可忽略**

```
cd ../ms-common
mvn install
```

### 启动Eureka Server

```
cd ../eureka
mvn clean spring-boot:run
```

### 启动Config Server

```
cd ../config
mvn clean spring-boot:run
```

### 启动你的Service

```
cd ../invoice-handling
mvn clean spring-boot:run -Dspring.profiles.active=local
```

## 构建Docker镜像

```
cd /invoice-handling
docker build -t aisino/si/invoice-handling .
docker push aisino/si/invoice-handling 
```


