[TOC]

# 读写分离测试

## 准备一个Mysql集群

http://note.youdao.com/noteshare?id=afb054d31ab7b89faef593f4ff734468&sub=2987691F751C4C469E3B9F599E5BAD9A

## 验证

停止master
```
docker stop mysql-cluster_mysql-master_1
```
**测试 添加账号**，失败
```
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue May 07 23:12:20 CST 2019
There was an unexpected error (type=Internal Server Error, status=500).
could not prepare statement; nested exception is org.hibernate.exception.JDBCConnectionException: could not prepare statement
```

**测试查询所有账号**, 成功

http://localhost:8080/userinfo

```
[{"id":1,"name":"wuhulala"},{"id":2,"name":"wuhulala2"}]
```

**重启master**

新增一个用户3  http://localhost:8080/userinfo/hlx

再次进行查询，会存在一定的延迟
```
[{"id":1,"name":"wuhulala"},{"id":2,"name":"wuhulala2"}]
```
稍等一下
```
[{"id":1,"name":"wuhulala"},{"id":2,"name":"wuhulala2"},{"id":3,"name":"hlx"}]
```
