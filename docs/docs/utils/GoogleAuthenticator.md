---
layout: post
title:  "net.renfei.sdk.utils.GoogleAuthenticator"
date:   2020-03-22 16:56:28
author: RenFei
---

# Google身份验证器
Google Authenticator是谷歌开源的Google身份验证器，常常用于两步验证（2-Step Verification）。
是基于TOTP算法(Time-based One-time Password algorithm)的实现。它使用加密哈希函数将密钥与当前时间戳组合在一起以生成一次性密码。 

### net.renfei.sdk.utils.GoogleAuthenticator.generateSecretKey
此方法可以生成用户秘钥，需要传入一个种子因数，这个因数应在服务器端配置好，在生成后需要记录在数据库中与用户绑定。案例：
```java
String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
```
### net.renfei.sdk.utils.GoogleAuthenticator.genTotpString
此方法用于获取TOTP字符串，将此字符串转换为二维码交给用户打开Google Authenticator进行扫描。参数依次是：厂商名、用户名、用户秘钥。案例：
```java
String secretKey = GoogleAuthenticator.generateSecretKey("abc123");
String totp = GoogleAuthenticator.genTotpString("RENFEI.NET", "Tester", secretKey);
```
### net.renfei.sdk.utils.GoogleAuthenticator.authcode
此方法用于将用户输入的TOTP密码和存储的用户秘钥进行验证是否正确。

<a href="/">Back 返回</a>