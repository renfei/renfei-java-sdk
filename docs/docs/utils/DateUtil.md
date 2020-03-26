---
layout: post
title:  "net.renfei.sdk.utils.DateUtils"
date:   2020-03-26 09:19:09
author: RenFei
---

# 时间类型工具
这里收集整理关于DateTime时间的常用工具方法。

### net.renfei.sdk.utils.DateUtils.getDate
期字符串，例如2015-08-11
### net.renfei.sdk.utils.DateUtils.getTime
时间字符串，例如 09:51:53
### net.renfei.sdk.utils.DateUtils.getDateTime
日期和时间字符串，例如 2015-08-11 09:51:53
### net.renfei.sdk.utils.DateUtils.getDate(java.lang.String)
获取当前时间指定格式下的字符串。
### net.renfei.sdk.utils.DateUtils.getDate(java.util.Date, java.lang.String)
获取指定日期的指定格式下的字符串。
### net.renfei.sdk.utils.DateUtils.formatDate
获取日期时间字符串，默认格式为（yyyy-MM-dd）。
### net.renfei.sdk.utils.DateUtils.getYear
获取当前年份字符串。
### net.renfei.sdk.utils.DateUtils.getMonth
获取当前月份字符串。
### net.renfei.sdk.utils.DateUtils.getDay
获取当前天数字符串。
### net.renfei.sdk.utils.DateUtils.getWeek
获取当前星期字符串。
### net.renfei.sdk.utils.DateUtils.parseDate
将日期型字符串转换为日期格式。
### net.renfei.sdk.utils.DateUtils.pastDays
获取当前日期与指定日期相隔的天数。
### net.renfei.sdk.utils.DateUtils.nextMinutes
获取当前日期指定分钟之后的日期。（负数是之前的日期）
### net.renfei.sdk.utils.DateUtils.nextHours
获取当前日期指定小时之后的日期。（负数是之前的日期）
### net.renfei.sdk.utils.DateUtils.nextDay
获取当前日期指定天数之后的日期。（负数是之前的日期）
### net.renfei.sdk.utils.DateUtils.nextMonth
获取当前日期指定月数之后的日期。（负数是之前的日期）
### net.renfei.sdk.utils.DateUtils.nextYear
获取当前日期指定年数之后的日期。（负数是之前的日期）
### net.renfei.sdk.utils.DateUtils.getCalendar
将 Date 日期转化为 Calendar 类型日期。
### net.renfei.sdk.utils.DateUtils.getDaysBetween
计算两个日期之间相差天数。
### net.renfei.sdk.utils.DateUtils.getWeeksBetween
计算两个日期之前相隔多少周。
### net.renfei.sdk.utils.DateUtils.getSpecifiedDayAfter
获取与指定日期间隔给定天数的日期。
### net.renfei.sdk.utils.DateUtils.dateMinus
计算两个日期之前间隔的小时数。
### net.renfei.sdk.utils.DateUtils.getCurrentSeason
获取当前季度 。
### net.renfei.sdk.utils.DateUtils.getIntervalBySeconds
将以秒为单位的时间转换为其他单位。
### net.renfei.sdk.utils.DateUtils.getNowTimeBefore
记录时间相当于目前多久之前。
### net.renfei.sdk.utils.DateUtils.getMonthsBetween
查询两个日期相隔的月份
### net.renfei.sdk.utils.DateUtils.getDayOfWeek
获取当前日期是星期几
### net.renfei.sdk.utils.DateUtils.snsFormat
sns 格式 如几秒前，几分钟前，几小时前，几天前，几个月前，几年后， ... 精细，类如某个明星几秒钟之前发表了一篇微博
### net.renfei.sdk.utils.DateUtils.getUnixTimestamp
获取当前时间戳

<a href="/">Back 返回</a>