# spring-cloud-system 架构
**1. 目录结果**
### cloud-eureka
### cloud-zuul
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
####   cloud-app-crawler //启动类
### cloud-server
####   cloud-server-audit
####   cloud-server-crawler
  

