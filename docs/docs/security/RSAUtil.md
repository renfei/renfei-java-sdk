---
layout: post
title:  "net.renfei.sdk.utils.RSAUtils"
date:   2020-03-22 17:32:51
author: RenFei
---

# RSA非对称加密工具
这里收集整理关于RSA非对称加密解密的常用工具方法。

### net.renfei.sdk.utils.RSAUtils.genKeyPair
此方法随机生成密钥对，返回一个Map，key=0表示公钥，key=1表示私钥
### net.renfei.sdk.utils.RSAUtils.encrypt
使用RSA公钥加密，参数依次是 待加密字符串、公钥字符串。
### net.renfei.sdk.utils.RSAUtils.decrypt
使用RSA私钥解密，参数依次是 待解密字符串、私钥字符串。

### 案例
```java
Map<Integer, String> keyMap = RSAUtils.genKeyPair(2048);
//加密字符串
String message = "test";
try {
    String encrypt = RSAUtils.encrypt(message, keyMap.get(0));
    System.out.println(keyMap.get(0));
    System.out.println(encrypt);
    System.out.println(keyMap.get(1));
    Assert.assertEquals(message, RSAUtils.decrypt(encrypt, keyMap.get(1)));
} catch (Exception ex) {
    ex.printStackTrace();
    Assert.assertNull(ex);
}
```

<a href="/">Back 返回</a>