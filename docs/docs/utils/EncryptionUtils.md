---
layout: post
title:  "net.renfei.sdk.utils.EncryptionUtils"
date:   2020-03-22 16:51:45
author: RenFei
---

# 通用加密工具
通用加密工具方便调用Java内置的加密算法。

### net.renfei.sdk.utils.EncryptionUtils.encrypt
例如在`net.renfei.sdk.utils.StringUtils.signature`中就使用了这个方法调用SHA1加密，案例：
```java
if (BeanUtils.isEmpty(arr)) {
    return null;
}
Arrays.sort(arr);
StringBuilder sb = new StringBuilder();
//将参数拼接成一个字符串进行sha1加密
for (String param : arr) {
    sb.append(param);
}
return EncryptionUtils.encrypt("SHA1", sb.toString());
```

<a href="/">Back 返回</a>