### 开发过程中的问题

#### 2020年3月10日
##### thymeleaf引入iframe的问题
* 使用`th:include`和`th:replace`都不行，出现问题
* **解决：**
    * 编写一个页面的controller，将页面的`src`设置为`<iframe frameborder="0"  th:src="@{/welcome}" class="x-iframe"></iframe>`
    
##### IDEA中commit代码之后在github上并没有看到提交的代码
* IDEA提交之后并没有push到github
* 解决办法：
    * 右击项目名，点击Git
    * 进入Repository,点击进入push
    * 点击右下角的Push
    
    
##### 提交表单出现问题：
1. 以post的方式提交表单会出现405错误，即
    ```
    There was an unexpected error (type=Method Not Allowed, status=405).
    Request method 'POST' not supported
    ```
    * 尝试很多方法，无法解决，不得已去掉form表单的`type=post`标识

2. 执行上述操作之后再次遇到问题，提交的数据会被放到地址栏，类似于以Get的方式再次提交到页面，由于表单提交的接口和获取页面的接口并不相同，所以在获取页面的接口中捕获异常并返回初始页面

#### 2020年4月22日
##### themeleaf内联表达式转义的问题
1. 位置：common.html:196
2. `[[${}]]`会转义特殊的字符，直接将中文转义为Unicode编码，所以中文使用`[(${})]`来解析

#### themeleaf的内联表达式在注释中的错误
1. 说明：themeleaf的内联表达式`[[&{}]]和[(${})]`在`<!--  -->`注释中一样会被解析