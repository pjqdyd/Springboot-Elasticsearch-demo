#### Win10 下 Docker 安装ElasticSearch (Linux下的Docker安装类似)


1.拉取镜像:
`docker pull elasticsearch`

2.查看镜像:
`docker images`

3.在D盘创建挂载卷目录(Win10 Docker设置确保D盘已勾选为可挂载目录):
D:\dockerdata\dockeres

4.运行容器(先不挂载目录):
`docker run -dit --name=myes -p 9200:9200 -p 9300:9300 elasticsearch:latest`

5.进入容器:
`docker exec -it myes /bin/bash`

6.运行ls查看目录:
这里我们要将/usr/share/elasticsearch/config配置文件目录和/usr/share/elasticsearch/data数据目录挂载到外部

`exit`退出.

7.新开一个cmd窗口运行(将配置和数据拷贝到宿主机外部):

`docker cp myes:/usr/share/elasticsearch/config D:\dockerdata\dockeres\`

`docker cp myes:/usr/share/elasticsearch/data D:\dockerdata\dockeres\`

8.结束并删除当前容器:

`docker stop myes`

`docker rm myes`

9.重新启动一个容器(并挂载配置和数据):

```
docker run -dit --name=myes -p 9200:9200 -p 9300:9300 -v D:\dockerdata\dockeres\config\:/usr/share/elasticsearch/config -v D:\dockerdata\dockeres\data\:/usr/share/elasticsearch/data  elasticsearch:latest
```

10.查看容器:
`docker ps`

11.浏览器访问http://localhost:9200/出现内容, 表明成功.

12.修改D:\dockerdata\dockeres\config\elasticserach.yml配置文件:
将 transport.host: 0.0.0.0 前的#去掉后保存文件退出。其作用是允许任何ip地址访问elasticsearch .开发测试阶段可以这么做，生产环境下指定具体的IP。

13.重启容器:
`docker restart myes`


(注意: 如果直接刚开始就挂载目录-v D:\dockerdata\dockeres\config\:/usr/share/elasticsearch/config容器是自动停止的, 因为在D盘的 D:\dockerdata\dockeres\config\下是没有配置文件的,所以要先拷贝一份容器配置文件到外部目录再挂载, 这样容器启动时就可以找到外部配置文件)
