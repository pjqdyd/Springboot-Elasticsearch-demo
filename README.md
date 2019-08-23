#### SpringBoot + Spring Data Elasticsearch整合的案例

#### 如何运行:

1. 确保已经安装好Elasticsearch, 并在安装目录下的**/config/elasticsearch.yml添加配置:

```
http.host: 0.0.0.0
transport.host: 0.0.0.0
```
作用是允许任何ip地址访问elasticsearch,开发测试阶段可以这么做，生产环境下指定具体的IP.


2. 配置项目的application.yml的连接配置.


3. 启动项目, 运行测试类中的方法(跟操作Jpa类似, Spring Data就是为了提供数据访问层统一的接口操作方式).



注意: 推荐使用docker来安装Elasticsearch, 参考[Docker-Elasticsearch.md](Docker-Elasticsearch.md)

