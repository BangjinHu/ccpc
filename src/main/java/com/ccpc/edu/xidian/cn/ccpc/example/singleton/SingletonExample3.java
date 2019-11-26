package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotRecommend;
import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;

/**
 * 懒汉模式，单例的实例在第一次使用进行创建（安全发布对象，线程安全的）
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private SingletonExample3(){}

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance(){
        if (null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
