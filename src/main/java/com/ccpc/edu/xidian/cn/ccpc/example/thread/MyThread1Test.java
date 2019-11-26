package com.ccpc.edu.xidian.cn.ccpc.example.thread;

public class MyThread1Test {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        System.out.println("begin=" + System.currentTimeMillis());
        myThread1.start();
        System.out.println("end=" + System.currentTimeMillis());
    }
}
