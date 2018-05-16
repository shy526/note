# node.js实现上传文件

## 参考
- [multipart协议](https://www.w3.org/Protocols/rfc1341/7_2_Multipart.html)
  - 遵守协议即可

## 实例

```js

const http=require("https");
const fs=require('fs');
const options={
      hostname:"sm.ms",
      path:'/api/upload',
      method:'POST'
  }
  const req=http.request(options,function(res){
      res.setEncoding("utf-8");
      res.on("data",function(chunk){
          console.log(chunk.toString())
      });
      console.log(res.statusCode);
  });
  req.on("error",function(err){
      console.log(err.message);
  });
  req.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
  uploadFile(req,'F:/xx.png',"smfile");



  /**
   * 上传文件(自动提交)
   * @param {request对象} req 
   * @param {上传文件的路径} filePath 
   * @param {fromName} name 
   */
  function uploadFile(req,filePath,name){
    let boundaryKey = Math.random().toString(16); // random string
    req.setHeader('Content-Type', 'multipart/form-data; boundary="'+boundaryKey+'"');
    req.write( 
      '--' + boundaryKey + '\r\n'
      + 'Content-Type: application/octet-stream\r\n' 
      + 'Content-Disposition: form-data; name="'+name+'"; filename="'+filePath+'"\r\n'
      + 'Content-Transfer-Encoding: binary\r\n\r\n' 
    );
    fs.createReadStream(filePath, { bufferSize: 4 * 1024 })
      .on('end', function() {
        req.end('\r\n--' + boundaryKey + '--'); 
      })
      .pipe(req, { end: false })
  }

```

```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-http-post上传文件实例"}
```
