package com.ccpc.edu.xidian.cn.ccpc.example.singleton;

import com.ccpc.edu.xidian.cn.ccpc.annoations.Recommend;
import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;

/**
 * 枚举模式，安全（推荐的安全发布对象模式）
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){}

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singletonExample7;
        //JVM保证该方法只调用一次
        Singleton(){
            singletonExample7 = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singletonExample7;
        }
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
