# 答题吧

### 介绍
一个基于`springboot`搭建的项目，项目包括完整的前后台，前台部分功能需要用户登录后才能使用，比如提问、积分兑换、举报等等，前台的问题搜索功能，使用`HanLP`分词器分词，搭配`MySQL`自带的全文搜索功能，实现问题的全文搜索，大大提高搜索准确度，提问的时候，可以邀约指定的人员帮助解答，通过邮箱通知被邀约的人员及时帮助解答，自己的提问有人解答后，以邮箱的方式告知自己，如果问题已得到解决或者不想再接收邮箱提醒，可关闭问题，以后的解答就不会再发邮件通知自己。前台的前端框架主要使用了`Layui`、`bootstrap`、`bootstrap-select`、`viewer`、`vue`；

### 软件架构



    +---java
    |   \---com
    |       \---dtb
    |           +---admin               后台管理系统相关
    |           |   +---controller      控制器相关类
    |           |   +---dao             Mapper的接口类
    |           |   \---service         Service接口类
    |           |       \---impl        Service接口实现类
    |           +---common
    |           |   \---controller      公共控制器相关类
    |           +---config                
    |           |   +---intercepors     拦截配置相关类
    |           |   \---mybatis         Mybatis配置相关类
    |           +---entity              实体类
    |           +---home                前台相关
    |           |   +---controller      控制器类
    |           |   +---dao             Mapper接口类
    |           |   \---service         Service接口类
    |           |       \---impl        Service接口实现类
    |           \---utils 
    |               +---email           邮件相关接口类
    |               |   \---impl        邮件相关接口实现类
    |               \---resulthandler   统一返回格式处理类
    \---resources
        +---dependents                  数据库脚本文件、使用说明等相关文件
        +---mapper  
        |   +---admin                   后台管理系统相关mapper文件
        |   \---home                    前台相关mapper文件
        +---static
        |   +---admin               
        |   |   +---css                 后台管理系统css相关文件
        |   |   +---fonts               后台管理系统fonts相关文件
        |   |   +---images              后台管理系统图片文件
        |   |   \---js  
        |   |       \---extends
        |   +---common
        |   |   \---plugs
        |   |       +---address         三级联动地址数据文件
        |   |       +---bootstrap       bootstrap框架相关文件
        |   |       |   +---css
        |   |       |   +---fonts
        |   |       |   \---js
        |   |       +---bootstrap-select bootstrap-select插件相关文件
        |   |       |   +---css
        |   |       |   \---js
        |   |       |       \---i18n
        |   |       +---cropper
        |   |       +---echarts         百度Echarts相关文件
        |   |       +---jquery          jQuery文件  
        |   |       +---layui           layui框架文件
        |   |       |   +---css
        |   |       |   |   \---modules
        |   |       |   |       +---laydate
        |   |       |   |       |   \---default
        |   |       |   |       \---layer
        |   |       |   |           \---default
        |   |       |   +---font
        |   |       |   +---images
        |   |       |   |   \---face
        |   |       |   \---lay
        |   |       |       \---modules
        |   |       +---time-line
        |   |       +---viewer          图片查看器文件
        |   |       \---vue             vue文件
        |   \---home
        |       +---images              前台图片文件夹
        |       |   +---system          前台系统相关文件
        |       |   \---user-head       
        |       \---js
        \---templates
            +---admin                   后台管理系统对应的模板文件夹
            |   +---admin               管理员相关模板文件
            |   +---carousel            
            |   +---document            文件相关模板文件
            |   +---echarts             统计相关模板文件
            |   +---feedback            反馈相关模板文件
            |   +---gift                积分兑换相关模板文件
            |   +---order               订单相关模板文件
            |   +---public              公共菜单、js、css等相关模板文件
            |   +---question            问题相关模板文件
            |   +---report              举报相关模板文件
            |   +---teacher             教师认证相关模板文件
            |   \---user                用户相关模板文件
            +---email                   模板邮件相关模板文件
            \---home                    前台相关模板文件
                \---public


### 安装教程

这里开发工具使用`IDEA`，`MySQL`数据库（版本5.6及其以上），对于这些工具的安装这里不做说明，网上有很多教程，这里就默认你的电脑上已经安装了`MySQL`数据库和IDEA开发工具。

1. 找到项目文件中的 resources/dependents/db_dtb.sql 数据库脚本文件，在自己的数据库执行，完成数据库相关表格以及数据的创建和录入；
2. 需要对`MySQL`数据库开启全文搜索功能，并且改变分词大小，在`my.ini`中配置两个地方，在`[mysqld]`下面添加代码

```
ft_min_word_len=2
ngram_token_size=2
```
   在[client]下添加代码
```
ft_min_word_len=2
```
3. 使用本系统，需要在 `application.properties` 中配置资源路径，这样上传的资源都会被保存在此文件夹中移动项目的时候也要复制该文件夹，不然会出现资源的404错误，默认如下代码段。如果你的电脑没有D盘或者想把资源路径放到别的磁盘，在配置文件`application.properties`中配置`com.dtb.file.baseFilePath`属性即可，格式如上；此项目中已有测试数据，所以需要把项目文件夹下面的 resources/dependents/dtb.zip 文件夹解压放到D盘根目录下；
```
com.dtb.file.baseFilePath=D://dtb//file/
```
4. 在 `application.properties` 配置文件中设置好自己的数据库用户名和密码；
5. 使用邮件功能，需要自己填写邮箱地址，并且开通三方平台授权功能，获得授权码，在 `application.properties` 配置文件中进行配置，如下：
```
spring.mail.username=你的邮箱地址
spring.mail.password=你的授权码，并非邮箱登录密码
```
6. 启动项目的启动文件，项目即可启动。

### 使用说明

1. 用户注册邮箱不可重复，并且注册后会发送激活邮件到注册邮箱，因此，项目运行，使用注册功能的时候，需要联网，不然无法发送邮件。


### 部分功能效果图
![image](https://gitee.com/FeiHongXianSen/datiba/raw/master/系统效果图/前台效果图/登录.png)
