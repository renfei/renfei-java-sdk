---
layout: post
title:  "net.renfei.sdk.utils.AESUtil"
date:   2020-03-22 16:23:20
author: RenFei
---

# AES对称加密工具
这里收集整理关于AES对称加密解密的常用工具方法。注意：由于此工具使用的128位，所以加解密的秘钥长度必须是16位长度的字符串。  
算法：AES  
模式：ECB  
填充：PKCS5Padding

### net.renfei.sdk.utils.AESUtil.base64Encode
此方法将字节数组转换为Base64字符串。
### net.renfei.sdk.utils.AESUtil.base64Decode
此方法将字符串换为字节数组。
### net.renfei.sdk.utils.AESUtil.aesEncryptToBytes
此方法将字符串使用传入的字符串秘钥进行AES加密，返回字节数组。
### net.renfei.sdk.utils.AESUtil.encrypt
此方法将字符串使用传入的字符串秘钥进行AES加密，返回Base64字符串。
### net.renfei.sdk.utils.AESUtil.aesDecryptByBytes
此方法传入密文字节数组和秘钥，返回解密后的字符串。
### net.renfei.sdk.utils.AESUtil.decrypt
此方法传入密文字符串和秘钥，返回解密后的字符串。

<a href="/">Back 返回</a>