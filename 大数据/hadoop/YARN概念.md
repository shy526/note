# YARN
- Hadoop的集群资源管理系统
- Hadoop2.0引入
- 改善MapReduce实现
- 具有足够的同性性
- 支持分布式计算模式
- 一般不会支持操作YARN 由分布式计算框架(spark,MApReduce)作为计算出'
  - 建立在spark,MApReduce,Tezde上的处理框架(Pig,Hive等)

## YARN应用机制
- 通过两类长期运行的守护进程提供核心服务
  - 管理集群上资源使用资源管理器(resource manager)
  - 运行在集群中所有节点上且能够启动和监控容器的节点管理器(node manger)
    - 容器可以是一个Unix进程也可以是Linux cgroup
      - 取决于YARN的配置

## 如何运行一个YARN应用
1. 客户端联系YARN要求它运行一个application master 进程
2. YARN找到一个能够在容器中启动 application master的节点管理器
3. 运行一个分布式计算

## 资源请求
1. 灵活的资源请求模型
    - 请求多个容器,可以指定每个容器需要的计算机资源数量,指定容器的本地限制要求
      - 本地化可以提升息率
      - 本地限制可以用于申请位于指定节点或机架,或集群中任何位置的容器
      - 本地话限制无法被满足时,要么不分配资源,要么放宽限制
      - 当启动一个容器处理HDFS数据块时
        - 会依次请求(前提都失败)
          - 本地节点
          - 存储副本的节点
          - 存储副本的机架中的一个节点
          - 集群中任意节点
    - 可以在运行中的任意时刻提出资源申请69

### 应用生命周期
1. 一个作业对应一个应用
    - MapReduce采用这种
2. 作业的每个会话或工作流对应一个应用
    - spark采用
    - 效率高
3. 多用户共享一个长期运行的应用
    - Apache Slider
      - 主要用于启动集群上的其他应用
    - 代理应用,避免启动先 application master的开销
    - 低延迟的查询响应

### 构建YARN应用
- 一般无需构建
- Apche Twill
  - 提供一个简单的编程模型
  - 允许将集群进程定义为java Runnable的扩展,然后在集群上的YARN容器中运行
  - 提供实时日志和命令消息的支持
- 可以参考YARN项目中distributed shell
  - 演示了如何使用YARN客户端API或与YARN守护进程之间的通信

## YARN中调度

### YARN中的三种调度器
- FIFO调度器(FIFO Scheduler)
  - 将应用放置在一个队列中然后按照提交顺序运行应用
  - 简单易懂,不需要配置,不适合共享集群
- 容量调度器(Capacity Scheduler)
  - 独立的专门的队列,保证小作业一提交就可以启动
    - 不至于被大作业卡住而等待
  - 相比FIFO大作业时间更长
- 公平调度器(Fair Scheduler)
  - 动态平衡资源
  - 使用相同资源时,必须等待上一个作业释放资源

### 容量调度器
- 允许多个组织共享一个Hadoop集群,每个组织可以分配到一部分资源
  - 为每个组织配置专门的队列
    - 单个作业使用的资源不能超过队列的容量
    - 队列中的多个作业可以使用额外的空闲资源
      - 不必考虑队列容量(弹性队列)
        - 会产生资源占用过多,其他队列等待的情况
          - 设置最大容量限制(牺牲弹性)
          - `yarn.scheduler.capacity.<queue-path>.user-limit-factor=1`
            - 1 为默认值
            - 1 即为弹性队列
- 不会强行中止来抢占资源
- 配置层次队列减少资源占用
  - `root`为所有队列的根目录
  - `yarn-site.xml`
    - 配置MapReduce使用调度器
  - 修改`capacity-scheduler.xml`
  - [官网文档](http://hadoop.apache.org/docs/r2.6.4/hadoop-yarn/hadoop-yarn-site/CapacityScheduler.html)

- 队列放置
  - `mapreduce.job.queuename`
    - 指定使用的队列

### 公平调度器
- 修改`yarn-site.xml`
  - `yarn.resoucemaneger.scheduler.class`配置为`org.apche.hadoop.yarn.server.resoucemaneger.scheduler.fair.FairScheduler`

- 队列配置
  - 默认文件名:`fair-scheduler.xml`
    - `yar.scheduler.fair.allocation.file`来修改文件名
  - 不配置的情况存放在以用户名命名的队列中
  - 支持层次队列
  - [官方文档](http://hadoop.apache.org/docs/r2.9.0/hadoop-yarn/hadoop-yarn-site/FairScheduler.html)

- 队列放置

- {type: "Hadoop", tag:"大数据,Hadoop,YARN",title:"YARN与调度器"}
