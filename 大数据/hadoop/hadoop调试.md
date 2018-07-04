# 调试

## 使用计数器和状态信息

```java
public class MaxTemperatureMapper
                //apper<输入键,输入值,输出键,输出值>
        extends  Mapper<LongWritable,Text,Text,IntWritable> {
    private static final int MISSING=9999;
    private NcdcRecordParser parser=new NcdcRecordParser();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value);
        if (parser.isValidTemperature()){
            System.err.println("温度超过100的行为: "+value);
            //更新map状态
            context.setStatus("发现了错误");
            //以枚举记录记录超过100度的记录数
            context.getCounter(Temperature.OVER_100).increment(1);
        }
        context.write(new Text(parser.getYear()),new IntWritable(parser.getAirTemperature()));
    }

   enum Temperature{
       OVER_100
   }
}
```
- 查看枚举计数器的数量
  - `mapred job -counter {JOBID} {类名$enum名} {枚举值}`

 ## 处理不合理的数据
- 检查不合理的数据并处理
```java
@Test
public void parsesMalformedTemperture() throws IOException {
    Text value=new Text("0335999999433181957042302005+37950+139117SAO +0004"+
            "RJSN V02011359003150070356999999433201957010100005+353");
    Counters counter = new Counters();
    MapDriver<LongWritable,Text,Text,IntWritable> test = new MapDriver<LongWritable, Text, Text, IntWritable>();
    test.withMapper(new MaxTemperatureMapper())
            //添加输入
            .withInput(new LongWritable(0),value).withCounters(counter).runTest();
    Counter counter1 = counter.findCounter(MaxTemperatureMapper.Temperature.OVER_100);
    System.out.println("counter1 = " + counter1.getValue());
}
```

## hadoop 日志
- YARN有一个日志聚合服务,可以去到已完成的应用的任务日志,并把它搬运到HDFS中
    - 被存储在一个容器文件中
- `yarn.log-aggregation-enable`
    - 通过设置这个属性
    - 默认关闭
    > 当设置为true时,会停止输出本地日志
- 详细查看[配置job历史] 

- 查看任务日志
  - `mapred job -logs {job_id}`
    - 展示的是容器数据
  
- hadoop日志类型

|        日志       |  主要对象 |                            描述                            |
|:---------------:|:-----:|:--------------------------------------------------------:|
|     系统守护进程日志    |  管理员  |        每个hadoop守护进程产生的,写入`HADOOP_LOG_DIV`环境变量定义的目录       |
|     HDFS审计日志    |  管理员  |         记录所有HDFS请求,默认关闭,写入地址可以配置,一般写入namenod的日志中         |
| MapRecuce作业历史日志 |   用户  |                  记录作业运行期间发生的事件,保存在HDFS中                  |
|  MapRecuce任务日志  |   用户  | 每个人物子进程都用log4j产生日志,一个保存标准输出,一个保存标准错误,写入目录由`YARN_LOG_DIR` |

- 例子

- mapper.java

```java
public class LoggingIdentityMapper extends Mapper {
    private final static Log LOG=LogFactory.getLog(LoggingIdentityMapper.class);

    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
        System.out.printf("Map key="+key);
        LOG.info("Map key="+key);
        if (LOG.isDebugEnabled()){
            LOG.debug("map value="+value);
        }
        context.write(key,value);
    }
}
```

- driver

```java
public class LoggingDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if(args.length!=2){
            System.err.printf("Usage: %s [generic options] <input> <output>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        Job job=new Job(getConf(),"日志mapper");
        job.setJarByClass(getClass());

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(LoggingIdentityMapper.class);
        job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new LoggingDriver(),args));
    }

}
```
> 日志默认:info级别

  ## 远程调试 