package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotRecommend;
import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;

/**
 * 双重同步锁单例模式
 * 懒汉模式，单例的实例在第一次使用进行创建（安全发布对象）非线程安全的，因为CPU和JVM的指令重排机制
 */
@NotThreadSafe
public class SingletonExample4 {

    private SingletonExample4(){}

    private static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance(){
        if (null == instance){//双重检测机制
            synchronized (SingletonExample4.class){//同步锁
                if (instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
