package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;

/**
 * 饿汉模式，单例的实例在类装载时候进行创建（安全发布对象）
 */
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2(){}

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
