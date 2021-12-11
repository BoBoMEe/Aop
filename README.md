# Android Aop 方案 AspectJ使用实例
---

![logcat](media/screen1.png "操作logcat日志")

![screenshot](media/record1.gif "手机操作演示")


# gradle 插件
---

引入方式：

```groovy
plugins {
    id 'com.android.application'
    id 'aop'
}
```

```groovy
 implementation 'com.bobomee.aop:annotation:1.0.0'
 implementation 'com.bobomee.aop:runtime:1.0.0'
```

# 参考blog
---

[Android切面编程AOP之AspectJ的使用](https://blog.csdn.net/wbwjx/article/details/121881427)
