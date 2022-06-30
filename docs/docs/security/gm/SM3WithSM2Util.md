---
layout: post
title:  "net.renfei.sdk.security.gm.sm.SM3WithSM2Util"
date:   2022-06-30 16:50:04
author: RenFei
---

# 国密-商密-SM3WithSM2算法

基于BouncyCastle实现国密算法SM2、SM3、SM4的操作类。参考了：https://github.com/Hyperledger-TWGC/java-gm

关于国密-商密的知识请参见：[https://dofm.org/zh-cn/security/guo-mi.html](https://dofm.org/zh-cn/security/guo-mi.html)

## SM3WithSM2

先散列然后非对称签名，直接签名内容会很大所以先散列

### net.renfei.sdk.security.gm.sm.SM3WithSM2Util.digestAndSign

摘要并签名

### net.renfei.sdk.security.gm.sm.SM3WithSM2Util.verifySignatureAndDigest

验证签名和摘要

<a href="/">Back 返回</a>