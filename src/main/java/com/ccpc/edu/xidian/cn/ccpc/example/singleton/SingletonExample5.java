package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;

/**
 * 双重同步锁单例模式
 * 懒汉模式，单例的实例在第一次使用进行创建
 */
@ThreadSafe
public class SingletonExample5 {

    private SingletonExample5(){}

    //volatile+双重检测机制，禁止指令重排
    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance(){
        if (null == instance){//双重检测机制
            synchronized (SingletonExample5.class){//同步锁
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
