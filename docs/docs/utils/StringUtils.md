---
layout: post
title:  "net.renfei.sdk.utils.StringUtils"
date:   2020-03-22 14:48:03 +0800
author: RenFei
---

# 字符串工具
这里收集整理关于字符串类的常用工具方法。

### net.renfei.sdk.utils.StringUtils.isEmail
此方法可用于判断字符串是否是一个Email邮箱地址的格式，案例：
```java
Assert.assertFalse(StringUtils.isEmail("w#test.com"));
Assert.assertFalse(StringUtils.isEmail("ab@cn"));
Assert.assertFalse(StringUtils.isEmail("ab@cn.*.com"));
Assert.assertFalse(StringUtils.isEmail("ab##@test.com"));
Assert.assertTrue(StringUtils.isEmail("test.test@test-test.com"));
Assert.assertTrue(StringUtils.isEmail("test.test@test.test.com"));
```
### net.renfei.sdk.utils.StringUtils.isChinaPhone
此方法可用于判断字符串是否是 **中国** 的手机号码，案例：
```java
Assert.assertTrue(StringUtils.isEmail("130001001234"));
```
### net.renfei.sdk.utils.StringUtils.isChinaMobilePhone
此方法可用于判断字符串是否是 **中国移动** 的手机号码。注意：由于中国大陆允许用户携号转网，对于携号转网的手机号并不能准确的判断。
### net.renfei.sdk.utils.StringUtils.isChinaUnicomePhone
此方法可用于判断字符串是否是 **中国联通** 的手机号码。注意：由于中国大陆允许用户携号转网，对于携号转网的手机号并不能准确的判断。
### net.renfei.sdk.utils.StringUtils.isChinaTelecomPhone
此方法可用于判断字符串是否是 **中国电信** 的手机号码。注意：由于中国大陆允许用户携号转网，对于携号转网的手机号并不能准确的判断。
### net.renfei.sdk.utils.StringUtils.isChinaMvnoPhone
此方法可用于判断字符串是否是 **虚拟运营商** 的手机号码。
### net.renfei.sdk.utils.StringUtils.signature
签名算法。先将传入的字符串列表进行字典排序，然后将排序后的列表拼接成新的字符串进行 **SHA1** 运算得到签名值

<a href="/">Back 返回</a>