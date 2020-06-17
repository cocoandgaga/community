 
##cocoandgaga's community 
 笔记： 
 1、
 springweb写法的简单例子
 [springWeb文档]         
 https://spring.io/guides/gs/serving-web-content/
 拦截器写法的简单例子
 [拦截器]
 https://docs.spring.io/spring/docs/5.2.7.RELEASE/spring-framework-reference/web.html#websocket-stomp-interceptors
 
 2、
 前端框架
 [Segment UI前端框架]  
 https://semantic-ui.com/introduction/advanced-usage.html
 
 [Bootstrap框架]
 https://v3.bootcss.com/components/#media
 
 3、
 查阅Thymeleaf语法资料
 [Thymeleaf官网]   
  https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
  
 4、
  应用使用OAuth协议接入到gitee的授权登陆操作流程 
  [OpenAPI]          
  https://gitee.com/api/v5/oauth_doc#/list-item-2
 
 5、
 spring热部署：
 [Developer Tools]:
 https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#using-boot-devtools
 
  插件 动态更新热部署过的页面
  [LiveReload]
  https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related
  
  idea相关操作
  设置：ctrl+shit+alt+?--->Registry---->complier.automake.allow.when.app.running勾选
  构建项目：Build Project
  
  6、
  数据表外键在java类的做法：
  例如：
  Question表字段creator关联User表字段account_id。
  做法：
  model层，拥有Question表的实体类和User表的实体类
  dto层，拥有QuestionDTo类，该类拥有Question表字段的类和一个private User user;
  service层，拥有QuestionService类，该类的功能是将Question表的creator值取出去查询User表并返回这个表对应id的user对象，然后添加到类型为QuestionDTO的list里，返回这个list
  controller层，调用service层的方法
  
  一些注意点：
  FastJason可以自动把jason串下划线命名映射为驼峰命名法
  提示没有getter也许是Mapper类sql字段写错
  启动失败可能是h2连接没有关闭
  提示template之类出错的可能是private字段没有用.getXX方法获取而直接.XX获取
  
  
  
  
 
 
 