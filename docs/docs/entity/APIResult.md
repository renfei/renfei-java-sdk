---
layout: post
title:  "net.renfei.sdk.entity.APIResult"
date:   2020-03-22 20:52:52
author: RenFei
---

# 统一接口响应对象
这是全局约定的统一响应对象

### net.renfei.sdk.entity.APIResult.stateCode
状态枚举，具体含义可参见<a href="/docs/comm/StateCode.html" target="_black">StateCode</a>文档。
### net.renfei.sdk.entity.APIResult.code
状态码，具体含义可参见<a href="/docs/comm/StateCode.html" target="_black">StateCode</a>文档。
### net.renfei.sdk.entity.APIResult.message
消息内容，可返回具体消息字符串。
### net.renfei.sdk.entity.APIResult.timestamp
响应时间戳，可用于与服务器时间比对和签名
### net.renfei.sdk.entity.APIResult.nonce
随机字符，可用于签名
### net.renfei.sdk.entity.APIResult.signature
签名，将时间戳+随机字符串字典排序后SHA1，可参见<a href="/docs/utils/StringUtils.html" target="_black">net.renfei.sdk.utils.StringUtils.signature</a>。
### net.renfei.sdk.entity.APIResult.data
数据对象的负载，装载实际传输的数据对象。
### net.renfei.sdk.entity.APIResult.builder().build()
APIResult的构建器，可以方便的构建APIResult对象。
### net.renfei.sdk.entity.APIResult.success()
可以快速简洁的返回成功状态的APIResult对象。

<a href="/">Back 返回</a>