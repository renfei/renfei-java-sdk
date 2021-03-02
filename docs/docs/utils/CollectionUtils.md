---
layout: post
title:  "net.renfei.sdk.utils.CollectionUtils"
date:   2021-03-02 20:29:44
author: RenFei
---

# 集合(Collection)工具
这里收集整理关于集合的处理方式，例如Map、List等集合  

### net.renfei.sdk.utils.CollectionUtils.comparison
此方法用于对比两个Hashtable集合，求出两个集合的交集、各自的补集，判断是否相等时会先判断Value，再判断Key，完全相等时才算交集。

此方法主要是为了在《 [大数据ETL技术中的数据抽取方法](https://www.renfei.net/posts/1003431) 》中实现两个集合比对所写的工具。

<a href="/">Back 返回</a>