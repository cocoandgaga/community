 
##cocoandgaga's community 
 笔记： 
 1、
 [springWeb文档]   
 https://spring.io/guides/gs/serving-web-content/

 2、 
 [拦截器]  
 https://docs.spring.io/spring/docs/5.2.7.RELEASE/spring-framework-reference/web.html#websocket-stomp-interceptors  
 [错误处理]  
 https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-error-handling
 
 
 3、
 前端框架  
 [Segment UI前端框架]  
 https://semantic-ui.com/introduction/advanced-usage.html  
 [Bootstrap框架]   
 https://v3.bootcss.com/components/#media
 
 4、
 [Thymeleaf官网]  
 https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
  
 4、
  应用使用OAuth协议接入到gitee的授权登陆操作流程  
  [OpenAPI]  
  https://gitee.com/api/v5/oauth_doc#/list-item-2
  
 5、
 整合数据库，适合多人协作  
 [flyway]  
 https://flywaydb.org/getstarted/firststeps/maven    
 application.properties添加：spring.flyway.baselineOnMigrate=true
 命令：  
 清除缓存mvn flyway:clean  
 执行：mvn flyway:migrate  

 
 6、
 Since Spring Boot applications are just plain Java applications, JVM hot-swapping should work out of the box.     
 JVM hot swapping is somewhat limited with the bytecode that it can replace.    
 spring热部署开发工具
 [Developer Tools]:  
 https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#using-boot-devtools  
   
  插件 动态更新热部署过的页面    
  [LiveReload]    
  https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related
  
  idea相关操作：  
  设置：ctrl+shit+alt+?--->Registry---->complier.automake.allow.when.app.running勾选  
  构建项目：Build Project
  
  7、 
  [mybatis generator]  
  It will introspect a database table (or many tables) and will generate artifacts that can be used to access the table(s).  
  参考配置：http://mybatis.org/generator/configreference/xmlconfig.html
  
  配置完了执行命令，自动生成mapper和model和mapper的xml：  
  mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
  
  8、
  [mybatis分页插件]  
  xml文件引入： 
 ```<plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>``` 
  用法：selectByExampleWithRowbounds，RowBounds提供了能返回指定限制长度和指定位置的记录，以便于分页。     
  参考链接：http://mybatis.org/generator/reference/plugins.html       
  
  9、
  [阿里云的文件存储oss]  
  将图片上传到阿里云oss
  参考文档：https://help.aliyun.com/document_detail/32016.html?spm=a2c4g.11186623.2.11.1d64a87cDlyZ9J#concept-32016-zh  
  先创建ram的用户得到accesskey，再创建accesskeyid
  
一些注意点：
  FastJason可以自动把jason串下划线命名映射为驼峰命名法  
  提示没有getter也许是Mapper类sql字段写错  
  启动失败可能是h2连接没有关闭  
  提示template之类出错的可能是private字段没有用.getXX方法获取而直接.XX获取  
   
   数据表外键在java类的做法：  
   例如：  
   Question表字段creator关联User表字段account_id。  
   做法：    
   model层，拥有Question表的实体类和User表的实体类  
   dto层，拥有QuestionDTo类，该类拥有Question表字段的类和一个private User user;  
   service层，拥有QuestionService类，该类的功能是将Question表的creator值取出去查询User表并返回这个表对应id的user对象，然后添加到类型为QuestionDTO的list里，返回这个list  
   controller层，调用service层的方法  
  
  
  
  
  mybatis generator使用：
  
  `TestTableExample example = new TestTableExample();  
  example.createCriteria().andField1EqualTo(5);  
  等同于：  
  TestTableExample example = new TestTableExample();  
  example.or().andField1EqualTo(5);   
  等同于:  
  where field1 = 5`  
   
  ` List<Integer> field5Values = new ArrayList<Integer>();   
    field5Values.add(8);  
    field5Values.add(11);  
    field5Values.add(14);  
    field5Values.add(22);   
    example.or()  
      .andField5In(field5Values);   
    example.or()  
      .andField6Between(3, 7);
  等同于：
     where (field5 in (8, 11, 14, 22))
        or (field6 between 3 and 7)`
        
  `Example类用来处理MBG动态查询功能. Example类设置条件用于动态WHERE子句下列方法中:  
  selectByExample  
  selectByExampleWithBLOBs  
  deleteByExample  
  countByExample  
  updateByExample`  
  
  `insert - will insert nulls
  insertSelective - ignores null properties
  UpdateByPrimaryKey - will set null values
  UpdateByPrimaryKey Selective - ignores null values`
  
 
 
 