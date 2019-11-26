package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;

/**
 * 懒汉模式，单例的实例在第一次使用进行创建（安全发布对象的方式1）
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造方法
    private SingletonExample1(){}

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if (null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
