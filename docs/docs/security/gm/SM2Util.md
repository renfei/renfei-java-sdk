---
layout: post
title:  "net.renfei.sdk.security.gm.sm.SM2Util"
date:   2022-06-30 16:50:04
author: RenFei
---

# 国密-商密-SM2算法

基于BouncyCastle实现国密算法SM2、SM3、SM4的操作类。参考了：https://github.com/Hyperledger-TWGC/java-gm

关于国密-商密的知识请参见：[https://dofm.org/zh-cn/security/guo-mi.html](https://dofm.org/zh-cn/security/guo-mi.html)

### net.renfei.sdk.security.gm.sm.SM2Util.generateKeyPair

生成 PKCS#10 证书请求

### net.renfei.sdk.security.gm.sm.SM2Util.encrypt

使用公钥加密内容

### net.renfei.sdk.security.gm.sm.SM2Util.decrypt

使用私钥解密内容

### net.renfei.sdk.security.gm.sm.SM2Util.sign

使用私钥对消息进行签名

### net.renfei.sdk.security.gm.sm.SM2Util.verify

使用公钥进行签名验签

### net.renfei.sdk.security.gm.sm.SM2Util.generateCSR

生成 CSR

### net.renfei.sdk.security.gm.sm.SM2Util.pemFrom

公私钥转字符串

### net.renfei.sdk.security.gm.sm.SM2Util.loadPrivateFromFile

加载私钥证书文件

### net.renfei.sdk.security.gm.sm.SM2Util.loadPublicFromFile

加载公钥证书文件

### net.renfei.sdk.security.gm.sm.SM2Util.loadX509CertificateFromFile

加载 X509 证书文件

### net.renfei.sdk.security.gm.sm.SM2Util.derivePublicFromPrivate

根据私钥生成公钥

<a href="/">Back 返回</a>