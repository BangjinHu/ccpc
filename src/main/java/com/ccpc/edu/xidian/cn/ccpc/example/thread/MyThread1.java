package com.ccpc.edu.xidian.cn.ccpc.example.thread;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run thread name:" + this.currentThread().getName() + "begin");
            Thread.sleep(2000);
            System.out.println("run thread name:" + this.currentThread().getName() + "end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
