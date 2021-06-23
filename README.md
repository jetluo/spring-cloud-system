# spring-cloud-system 架构
**1. 目录结果**
### cloud-eureka
### cloud-app-sso 添加Oauth2.0
### cloud-zuul 添加一次性请求令牌

前端生成token：

const _generateOnceToken = (protocol, salt) => {

​    let token = {

​      token: uuid.v4(),  //uuid

​      timestamp: new Date().getTime(), //当前时间戳

​      sign: null

​    }

​    let value = token.timestamp + ',' + token.token //当前时间戳+uuid+盐

​    if (salt) {

​        value = value + ',' + salt

​    }

​    token.sign = smcrypto.Sm3Utils.encrypt(value)  //sm3加密

​    return token

}

前端绑定token到header：

​        return {

​            'X-Request-Token': onceToken.token,

​            'X-Request-Time': onceToken.timestamp,

​            'X-Request-Sign': onceToken.sign

​        }

后端完成验证。

![image-20210606122338118](assets/image-20210606122338118.png)

 ![image-20210606122445133](assets/image-20210606122445133.png)

### cloud-app
####   cloud-app-audit  //启动类
## 审计功能完成业务数据访问的拦截并存入到数据库。
## 1.根据sql脚本在数据库创建审计日志表
## 2.在启动类添加扫描包 
##  @MapperScan(basePackages= {"com.cloud.kjetboy.server.audit.mapper"})
##  @SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.audit"})
## 3.在需要添加审计的controller类上添加注解@Audit
## 暂时不实现展现功能
## 上面是单体模式，如果是审计的client->server模式。
## 1. client就是这个 cloud-app-audit单体项目，在写入数据库的时候可以用http模式调用服务端
## 2。 服务端接收服务，完成数据写入 。 然后在查询出数据，完成数据分类，实现审计服务。
## 核心类图
![img.png](img.png)
####   cloud-app-crawler //启动类
### cloud-server
####   cloud-server-audit
####   cloud-server-crawler
####   cloud-server-sso //授权服务
==================
## 基于oltu
## 
## 前端完成，后端提供服务即可。
## 第一步：获取code，并跳转到访问页面
## admin/123456
## http://localhost:18888/authorize?response_type=code&client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&redirect_uri=http://localhost:18888/v1/openapi/userInfo
## 
## 
## 第二步：根据code，获取到access_token
## http://localhost:18888/accessToken?grant_type=authorization_code&code=ee744975d49d104caa835c37a5d1196a&redirect_uri=http://localhost:18888/v1/openapi/userInfo&client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&client_secret=d8346ea2-6017-43ed-ad68-19c0f971738b
##
##
## 第三步：根据access_token，获取数据。
## http://localhost:18888/v1/openapi/userInfo?access_token=98422b1e02cdb35c25bf178cde9856c8
## 前端完成第一步请求，检查没有token的时候调用/authorize api，进行登录验证和获取access_token，返回后第三步，前端获取access_token并存储起来，
## 并通过axios拦截器完成统一配置，每次请求发出去之前把token加到请求头。
## 后端需要加过滤器，添加对请求access_token的检查即可。
## 第一步：检查access_token
## http://localhost:18888/checkAccessToken?accessToken=98422b1e02cdb35c25bf178cde9856c8
## 前后端分离应用，由前端完成用户登录的校验，获取到token，通过网关去访问系统资源，网关进行判断是否是有效的token
## 有效则调用对应的服务，无效则进行拦截并提示token无效。
