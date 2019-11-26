package com.ccpc.edu.xidian.cn.ccpc.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExchangerExample1 {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(()->{
            try {
                String A = "bank 1";
                exchanger.exchange(A);
            }catch (InterruptedException e){
                log.error("exception", e);
            }
        });

        executorService.execute(()->{
            try {
                String B = "bank 2";
                String A = exchanger.exchange("B");
                System.out.println("data A equals B ?" + A.equals(B) + " A:" + A + " B:" + B);
            }catch (Exception e){
                log.error("exception", e);
            }
        });

        executorService.shutdown();
    }
}
