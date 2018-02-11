# IO 操作

## HDFS数据完整性
- 会对所有写入数据计算校验和,并在读取数据时验证校验和
  - `dfs.bytes-per-checksum`指定的字节的数据计算校验的和
    - 默认512,有CRC-32校验
- datanode负责收到数据后的存储及其校验和之前数据进行验证
  - 在收取数据,和复制其他datanode数据时执行
  - 在管线中由最后一个datanode负责验证校验和,不符合抛出异常
- 读取数据时,也会验证校验和
  - 与datanode存储的校验和进行比较
  - 每个datanode都会保存一个用于验证的校验和日志
- 定期校验数据块
  - datanode会定期校验本地所有的数据块
    - 损坏时,通过数据副本来修复损坏的数据块
- 使用`FileSystem.setVerifyChecksum(false)` 禁用校验
- 通过`- get -ignoreCrc`命令或者`-copyTolocal`
  - 的可以达到禁用校验和的效果
- `fs -checksum`
  - 来检查一个文件的校验和

- `LocalFileSystem`
  - 执行客户端的校验和验证
  - 禁用和校验
    - 使用`RowLocalFileSystem`对象
  - 全局校验和验证
      - 将`fs.flie.impl`替换为`org.apache.hadoop.fs.RawLocalFileSystem`
      - 新建一个`RowLocalFileSystem`实例
      ```java
        Configurtion conf=...
        FileSystem fs=new RowLocalFileSystem();
        fs.initialize(null,conf);
      ```
- `ChecksumFileSystem`
  - 向无校验和系统中加入校验和
  ```java
  FileSystem rawfs=...
  FileSystem checksummedfd= new ChecksumFileSystem(rawfs);
  ```
  - raw文件系统可以使用 `ChecksumFileSystem.getRawFileSystem()`来获取

## 压缩
1. 减少文件所需要的磁盘控件
2. 加快数据在网络和磁盘上的传输

| 压缩格式 | 工具  | 算法    | 文件扩展名 | 是否可切分 | 是否java实现 | 是否有原生实现 |
| -------- | ----- | ------- | ---------- | ---------- | ------------ | -------------- |
| DEFLATE  | 无    | DEFLATE | .defalate  | 否         | 是           | 是             |
| gzip     | gzip  | DEFLATE | .gz        | 否         | 是           | 是             |
| bzip2    | bzip2 | bzip2   | .bz2       | 是         | 是           | 否             |
| LZO      | lzop  | LZO     | .lzo       | 否         | 否           | 是             |
| LZ4      | 无    | LZ4     | .LZ4       | 否         | 否           | 是             |
| Snappy   | 无    | Snappy  | .snappy    | 否         | 否           | 是               |
> LAZO已经被索引了,那么LZO可以切分
> `io.native.lib.available`为false时,可确保使用内置java代码库

- 压缩工具
  - `-1`: 优化压缩数度
  -  `-9`: 优化压缩空间

- CodecPool
  -压缩池,反复使用时压缩解压缩时,避免创建这种对象的开销

- 压缩和输入分片
  - 不会切分gzip压缩文件(gzip不支持切分),牺牲了数据本地性,一个map处理8个HDFS,任务数减少,作业的粒度减少,运行时间变长
  - 压缩策略
    - 使用容器文件格式(Avro数据文件,ORCFiles或者Parquet) 这些文件同时支持压缩和切分,通常最好与一个快速压缩工具配合使用
      - LZO,LZ4,Snappy
    - 使用支持切分的压缩格式
      - bzip2
      - 通过索引实现切分的LZO
    - 在应用中将文件切分成块
      - 使用任意一种压缩工具
      - 需要合理选择数据块大小
    - 存储未经压缩的文件
    - > 大文件不要使用不支持切分的压缩格式,不然导致MapReduce效率底下

- MapReduce中使用压缩
  -
