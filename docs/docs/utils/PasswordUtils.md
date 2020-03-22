---
layout: post
title:  "net.renfei.sdk.utils.PasswordUtils"
date:   2020-03-22 17:25:01
author: RenFei
---

# 密码工具
这里收集整理关于密码的工具方法。此工具使用`SHA1`加密方式，并使用加盐、多次迭代的方式加密密码。

### net.renfei.sdk.utils.PasswordUtils.createHash(java.lang.String)
此方法将明文密码加密为密文，方便存储到数据库。案例：
```java
String password = "MyPassword";
String correctHash = PasswordUtils.createHash(password);
```
### net.renfei.sdk.utils.PasswordUtils.verifyPassword(java.lang.String, java.lang.String)
此方法用于验证明文密码和密文密码是否一致，案例：
```java
String password = "MyPassword";
String correctHash = PasswordUtils.createHash(password);
Assert.assertTrue(PasswordUtils.verifyPassword(password, correctHash));
```

<a href="/">Back 返回</a>