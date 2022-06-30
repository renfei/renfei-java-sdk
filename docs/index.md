---
layout: default
---
<h1 align="center">RenFei SDK for Java</h1>

<p align="center">
<a href="https://search.maven.org/search?q=g:%22net.renfei%22%20AND%20a:%22sdk%22" target="_blank"><img src="https://img.shields.io/maven-central/v/net.renfei/sdk.svg?label=Maven%20Central" alt="Latest Stable Version"/></a>
<a href="https://travis-ci.org/renfei/renfei-java-sdk" target="_blank"><img src="https://travis-ci.org/renfei/renfei-java-sdk.svg?branch=master"/></a>
<a href='https://coveralls.io/github/renfei/renfei-java-sdk?branch=master' target="_blank"><img src='https://coveralls.io/repos/github/renfei/renfei-java-sdk/badge.svg?branch=master' alt='Coverage Status' /></a>
<a href="https://codebeat.co/projects/github-com-renfei-renfei-java-sdk-master" target="_blank"><img src="https://codebeat.co/badges/f0436930-23f8-4224-9f23-1a29a22d69d1" /></a>
<a href="https://ci.appveyor.com/project/NeilRen/renfei-java-sdk" target="_blank"><img src="https://ci.appveyor.com/api/projects/status/p4mfa2qpy1tbqxj9?svg=true"/></a>
</p>

<a href="https://www.renfei.net" target="_black">任霏主页</a>
<a href="https://github.com/renfei/renfei-java-sdk" target="_black">项目主页</a>

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
> 您需要先提前下载好对应版本的Jar包，您可以<a href="https://github.com/renfei/renfei-java-sdk/packages/152444" target="_black">到这里</a>下载，在页面右侧的【Assets】下面点击对应的sdk-xx.jar即可将Jar包下载到本地。

将下载好的Jar包复制到您项目中的 _lib_ 文件夹中，在选择项目右键属性，选择添加库，添加Jar包即可引用到您的项目中。

# 工具目录

- net.renfei.sdk
  - comm
    - [StateCode](docs/comm/StateCode.html)
  - entity
    - [APIResult](docs/entity/APIResult.html)
  - http
  - utils
    - [AESUtil](docs/security/AESUtil.html)
    - [RSAUtils](docs/security/RSAUtils.html)
    - [SHAUtil](docs/security/SHAUtil.html)
    - [SM2Util](docs/security/gm/SM2Util.html)
    - [SM3Util](docs/security/gm/SM3Util.html)
    - [SM3WithSM2Util](docs/security/gm/SM3WithSM2Util.html)
    - [SM4Util](docs/security/gm/SM4Util.html)
    - [ChinaIdCardUtils](docs/utils/ChinaIdCardUtils.html)
    - [CollectionUtils](docs/utils/CollectionUtils.html)
    - [DateUtil](docs/utils/DateUtil.html)
    - [GoogleAuthenticator](docs/utils/GoogleAuthenticator.html)
    - [ListUtils](docs/utils/ListUtils.html)
    - [NumberUtils](docs/utils/NumberUtils.html)
    - [PasswordUtils](docs/utils/PasswordUtils.html)
    - [StringUtils](docs/utils/StringUtils.html)


