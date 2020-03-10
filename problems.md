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