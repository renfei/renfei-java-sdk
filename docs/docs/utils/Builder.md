---
layout: post
title:  "net.renfei.sdk.utils.Builder"
date:   2020-03-22 16:23:20
author: RenFei
---

# 构建工具
构建工具可以方便快递的构建一个对象实体。

### net.renfei.sdk.utils.Builder.of().with().build()
使用方法为调用of()进行实例化，再使用.with()填充对象内容，最后使用.build()完成构建。案例：
```java
LogDO logDO = Builder.of(LogDO::new)
        .with(LogDO::setUuid, UUID.randomUUID().toString())
        .with(LogDO::setDatetime, new Date())
        .with(LogDO::setLevel, "INFO")
        .with(LogDO::setInorout, "INPUT")
        .with(LogDO::setRemoteIp, "123.123.123.123")
        .with(LogDO::setRemoteUser, "tester")
        .with(LogDO::setRemoteAgent, "")
        .with(LogDO::setRequestUrl, "http://localhost/")
        .with(LogDO::setRequestMethod, "GET")
        .with(LogDO::setStatusCode, "200")
        .with(LogDO::setLogValue, "Get Log")
        .build();
```

<a href="/">Back 返回</a>