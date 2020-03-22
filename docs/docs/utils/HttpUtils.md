---
layout: post
title:  "net.renfei.sdk.utils.HttpUtils"
date:   2020-03-22 17:07:54
author: RenFei
---

# Http请求工具
二次封装org.apache.http.client，更方便的使用网络请求。

### net.renfei.sdk.utils.HttpUtils.get
发送一个Get请求，参数是net.renfei.sdk.http.HttpRequest，案例：
```java
HttpRequest request = HttpRequest.create().url("http://ip.renfei.net");
HttpResult result = HttpUtils.get(request);
```
### net.renfei.sdk.utils.HttpUtils.post
发送一个Post请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.put
发送一个Put请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.header
发送一个Header请求，参数是net.renfei.sdk.http.HttpRequest

### net.renfei.sdk.utils.HttpUtils.patch
发送一个Patch请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.delete
发送一个Delete请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.trace
发送一个Trace请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.options
发送一个Options请求，参数是net.renfei.sdk.http.HttpRequest
### net.renfei.sdk.utils.HttpUtils.down
此方法可以用于下载文件，将返回java.io.OutputStream对象。

<a href="/">Back 返回</a>