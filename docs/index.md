---
layout: default
---
<h1 align="center">RenFei SDK for Java</h1>

<p align="center">
<a href="https://search.maven.org/search?q=g:%22net.renfei%22%20AND%20a:%22sdk%22" target="_black"><img src="https://img.shields.io/maven-central/v/net.renfei/sdk.svg?label=Maven%20Central" alt="Latest Stable Version"/></a>
<a href="https://travis-ci.org/NeilRen/renfei-java-sdk" target="_black"><img src="https://travis-ci.org/NeilRen/renfei-java-sdk.svg?branch=master"/></a>
<a href='https://coveralls.io/github/NeilRen/renfei-java-sdk?branch=master' target="_black"><img src='https://coveralls.io/repos/github/NeilRen/renfei-java-sdk/badge.svg?branch=master' alt='Coverage Status' /></a>
<a href="https://codebeat.co/projects/github-com-neilren-renfei-java-sdk-master" target="_black"><img src="https://codebeat.co/badges/8fc75bd7-f1c3-4383-bbec-e752d71138d2" /></a>
<a href="https://ci.appveyor.com/project/NeilRen/renfei-java-sdk" target="_black"><img src="https://ci.appveyor.com/api/projects/status/ym3ev2dx20715too?svg=true"/></a>
</p>

<a href="https://www.renfei.net" target="_black">任霏主页</a>
<a href="https://github.com/renfei-net/renfei-java-sdk" target="_black">项目主页</a>

**sdk.renfei.net**是开发中常用的工具代码，以方便在各个项目中重新利用它们。虽然程序员们都热衷于重复的"造轮子"，但这样是不对的。

# 安装依赖

> RenFei SDK for Java 需要1.8及以上的JDK。
>
> 此工具包只适用于Java项目

### 通过Maven来管理项目依赖(推荐)

> 如果您使用Apache Maven来管理Java项目，只需在项目的`pom.xml`文件加入相应的依赖项即可。
>
> 您只需在`pom.xml`中声明以下两赖：

```xml
<dependency>
    <groupId>net.renfei</groupId>
    <artifactId>sdk</artifactId>
    <version>{{ site.sdk.version }}</version>
</dependency>
```

### 通过引用Jar包安装
> 您需要先提前下载好对应版本的Jar包，您可以<a href="https://github.com/renfei-net/renfei-java-sdk/packages/152444" target="_black">到这里</a>下载，在页面右侧的【Assets】下面点击对应的sdk-xx.jar即可将Jar包下载到本地。

将下载好的Jar包复制到您项目中的 _lib_ 文件夹中，在选择项目右键属性，选择添加库，添加Jar包即可引用到您的项目中。

# 工具目录

- net.renfei.sdk
  - comm
  - entity
  - http
  - utils
    - [AESUtil](docs/utils/AESUtil.html)
    - [BeanUtils](docs/utils/BeanUtils.html)
    - [Builder](docs/utils/Builder.html)
    - [EncryptionUtils](docs/utils/EncryptionUtils.html)
    - [GoogleAuthenticator](docs/utils/GoogleAuthenticator.html)
    - [HttpUtils](docs/utils/HttpUtils.html)
    - [IpUtils](docs/utils/IpUtils.html)
    - [ListUtils](docs/utils/ListUtils.html)
    - [PasswordUtils](docs/utils/PasswordUtils.html)
    - [RSAUtils](docs/utils/RSAUtils.html)
    - [StringUtils](docs/utils/StringUtils.html)


