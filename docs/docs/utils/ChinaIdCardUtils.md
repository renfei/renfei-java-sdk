---
layout: post
title:  "net.renfei.sdk.utils.ChinaIdCardUtils"
date:   2021-03-02 20:29:44
author: RenFei
---

# 中国身份证号码工具
这里收集整理关于中国身份证号码的工具，支持香港、澳门、台湾地区身份证号。 

### net.renfei.sdk.utils.ChinaIdCardUtils.conver15CardTo18
此方法用于将15位身份证号码转换为18位，中国旧身份证为15位，二代身份证为18位。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateCard
此方法用于验证身份证号码是否合法，主要是校验最后一位校验位是否正确。此方法兼容15位身份证号或18位身份证号。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateIdCard18
此方法用于验证18位身份证号码是否合法，主要是校验最后一位校验位是否正确。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateIdCard15
此方法用于验证15位身份证号码是否合法，主要是校验最后一位校验位是否正确。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateIdCard10
此方法用于验证10位身份编码是否合法。主要用于台湾、澳门、香港的身份证号。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateTWCard
此方法用于验证台湾身份证号码是否合法。

### net.renfei.sdk.utils.ChinaIdCardUtils.validateHKCard
此方法用于验证香港身份证号码(存在Bug，部份特殊身份证无法检查)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getAgeByIdCard
此方法用于根据身份编号获取年龄。

### net.renfei.sdk.utils.ChinaIdCardUtils.getBirthByIdCard
此方法用于根据身份编号获取生日(yyyyMMdd)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getYearByIdCard
此方法用于根据身份编号获取生日年(yyyy)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getMonthByIdCard
此方法用于根据身份编号获取生日月(MM)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getDateByIdCard
此方法用于根据身份编号获取生日天(dd)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getGenderByIdCard
此方法用于根据身份编号获取性别(M - 男 ， F - 女 ， N - 未知)。

### net.renfei.sdk.utils.ChinaIdCardUtils.getProvinceByIdCard
此方法用于根据身份编号获取户籍省份省级编码。

<a href="/">Back 返回</a>