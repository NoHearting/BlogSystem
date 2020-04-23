### 开发日志

#### 2020年3月10日
* 创建项目，将静态文件导入项目
* 将后台管理模板全部更改为适配thymeleaf的语法，修改所有资源引入的方式，并且为每个页面创建controller
* 将博客系统所有模板全部更改为适配thymeleaf的语法，修改所有资源的引入方式，并且为每个页面创建controller

#### 2020年3月13日
* 新建博客显示页面，并将`editormd`解析器集成到网页中。
* 集成时会遇到`editormd`中的CSS样式和原来框架CSS样式冲突的问题，所以将`editormd`放入`iframe`中。
* 使用iframe并不会自动适应内容大小来改变外部容器的大小，这里需要使用JS动态设置其高度。
* 动态设置高度使用内部iframe加载完成后获取高度之后调用父窗口的函数设置容器的高度。
* 上述设置高度出现了一个bug，有时候页面获取的高度会小于实际高度，导致容器出现滚动条。
    + 这是由于子窗口调用父窗口，其中iframe会存在缓存的原因
    + 解决办法是在iframe的src请求每次加上一个时间戳
    
#### 2020年3月14日
* 完成查找特定博客并显示的功能，目前只能显示主页上的所有博客和特定博客。


#### 2020年4月22日
##### 1、配置Mybatis Log Plugin，打印SQL执行日志
在配置文件中添加配置
```$xslt
mybatis:
  mapper-locations: classpath:mapping/*.xml
  #type-aliases-package: com.example.entity

logging:
  level:
    com:
      example:
        mapper: debug
```