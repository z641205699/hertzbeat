---
id: kafka  
title: 监控：Kafka监控      
sidebar_label: Kafka监控
---

> 对Kafka的通用性能指标进行采集监控

**使用协议：JMX** 

### 配置参数

| 参数名称      | 参数帮助描述 |
| ----------- | ----------- |
| 监控Host     | 被监控的对端IPV4，IPV6或域名。注意⚠️不带协议头(eg: https://, http://)。 |
| 监控名称     | 标识此监控的名称，名称需要保证唯一性。  |
| 查询超时时间 | 设置Kafka连接的超时时间，单位ms毫秒，默认3000毫秒。  |
| 用户名      | JMX连接用户名 |
| 密码        | JMX连接密码 |
| 采集间隔    | 监控周期性采集数据间隔时间，单位秒，可设置的最小间隔为30秒  |
| 是否探测    | 新增监控前是否先探测检查监控可用性，探测成功才会继续新增修改操作  |
| 描述备注    | 更多标识和描述此监控的备注信息，用户可以在这里备注信息  |

### 采集指标


#### 指标集合：server_info

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| Version         | 无 | Kafka版本 |
| StartTimeMs            | ms | 运行时间 |
| CommitId         | 无 | 版本提交ID |


#### 指标集合：code_cache

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| committed            | kb | 总量 |
| init         | kb | 初始化大小 |
| max | kb | 最大 |
| used         | kb | 已使用 |

#### 指标集合：active_controller_count

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| Value            | 个 | 活跃监控器数量 |



#### 指标集合：broker_partition_count

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| Value            | 个 | 分区数量 |

#### 指标集合：broker_leader_count

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| Value            | 个 | 领导者数量 |


#### 指标集合：broker_handler_avg_percent 请求处理器空闲率

| 指标名称      | 指标单位 | 指标帮助描述 |
| ----------- | ----------- | ----------- |
| EventType            | 无 | 类型 |
| RateUnit            | 具体情况具体分析 | 单位 |
| Count            | 个 | 数量 |
| OneMinuteRate            | % | 一分钟处理率 |
| FiveMinuteRate            | % | 五分钟处理率 |
| MeanRate            | 无 | 平均处理率 |
| FifteenMinuteRate            | 无 | 十五分钟处理率 |


> 其他指标见文知意

### Kafka开启JMX协议步骤

1. 安装Kafka

2. 修改启动脚本

找到Kafka安装目录/bin/kafka-server-start.sh在 倒数第二行添加如下内容,⚠️注意替换端口和IP地址

```shell
export JMX_PORT=9999;
export KAFKA_JMX_OPTS="-Djava.rmi.server.hostname=ip地址 -Dcom.sun.management.jmxremote.rmi.port=9999 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false";

//  这是最后一行本来就存在的
exec $base_dir/kafka-run-class.sh $EXTRA_ARGS kafka.Kafka "$@"
```


