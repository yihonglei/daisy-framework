**文件传输组件**
- 目前集成了seaweedFs和ftp两种方式上传文件。引入此包并在yaml文件中配置相关配置即可选择使用哪种工具进行上传。

- 引入方式
```xml
<dependency>
      <groupId>com.hq.file</groupId>
      <artifactId>file-transfer-starter</artifactId>
      <version>1.0-SNAPSHOT</version>
</dependency>
```
seaweedFs工具内部日志框架使用commons-logging，如果需要打印seaweedFs内部日志，需要显式引入
```xml
<dependency>
     <groupId>org.slf4j</groupId>
     <artifactId>jcl-over-slf4j</artifactId>
</dependency>
```
ftp工具使用的Apache common-net包下的FTPClient

- 配置文件添加配置
```yaml
file:
      upload:
        seaweed:
          enable: true                             #必填，当未配置或值为false时不会注入seaweed客户端
          host: test-inside-weedfs.hqzhuanche.com  #必填，seaweed服务地址
          port: 9333                               #选填，默认端口就9333
          connectTimeout: 3000                     #选填，默认超时时间为3000毫秒
          viewUrl: https://test-files.hqzhuanche.com #选填，文件对外展示域名
          insideViewUrl: http://test-inside-files.hqzhuanche.com #选填，文件内部展示域名
        ftp:
          enable: true                             #必填，当未配置或值为false时不会注入ftp客户端
          host: inside-ftp.hqzhuanche.com          #必填，ftp服务地址
          rootDir: /upload                         #选填，上传ftp根目录地址，默认为/upload文件夹
          userName: ftpuser                        #必填，ftp用户名
          password: hqftppAss                      #必填，ftp密码
          viewUrl: https://images.hqzhuanche.com   #选填，文件对外展示域名
          insideViewUrl: https://images.hqzhuanche.com #选填，文件内部展示域名
```
需要哪种工具上传只需要配置需要的工具配置即可

- 使用方式
```java
//seaweedFs客户端注入
@Autowired
private SeaweedFsClient seaweedFsClient;
//seaweedFs上传文件
FileUploadResult result = seaweedFsClient.fileUpload(new FileInputStream(file));
//ftp客户端注入
@Autowired
private FtpClient ftpClient;
//使用默认路径上传
FileUploadResult result = ftpClient.fileUpload("fileName", new FileInputStream(file));
//使用指定路径上传
FileUploadResult result = ftpClient.fileUpload("fileName", "/path", new FileInputStream(file));
```
result为空时表示文件上传失败