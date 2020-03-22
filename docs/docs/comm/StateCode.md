---
layout: post
title:  "net.renfei.sdk.comm.StateCode"
date:   2020-03-22 20:52:52
author: RenFei
---

# 状态码
这是全局约定的状态码

### net.renfei.sdk.comm.StateCode.OK
状态码`200`，代表执行成功。
### net.renfei.sdk.comm.StateCode.Accepted
状态码`202`，已经接受到请求，正在执行，但是不知道是否成功，用于异步请求。
### net.renfei.sdk.comm.StateCode.NoContent
状态码`204`，代表服务器成功处理，但未返回内容。
### net.renfei.sdk.comm.StateCode.Reset
状态码`205`，服务器处理成功，用户终端应重置文档视图，重新拉取数据。
### net.renfei.sdk.comm.StateCode.Partial
状态码`206`，代表服务器成功处理了部分任务，有部分任务没有成功。
### net.renfei.sdk.comm.StateCode.BadRequest
状态码`400`，代表客户端请求的语法错误，服务器无法理解，一般是因为参数不正确。
### net.renfei.sdk.comm.StateCode.Unauthorized
状态码`401`，代表要求用户的身份认证，原因是没有登录。
### net.renfei.sdk.comm.StateCode.NeedTOTP
状态码`402`，代表用户开启了两步验证，需要TOTP双因子验证码。
### net.renfei.sdk.comm.StateCode.Forbidden
状态码`403`，代表权限不足，服务器理解请求客户端的请求，但是拒绝执行此请求。
### net.renfei.sdk.comm.StateCode.Error
状态码`500`，代表服务器发生了错误，服务暂时不可用。
### net.renfei.sdk.comm.StateCode.Unavailable
状态码`503`，代表服务器可能正在维护，服务暂时不可用。

<a href="/">Back 返回</a>