package com.ccpc.edu.xidian.cn.ccpc.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class HashMapExample {

    //请求总数
    public static int clientTotal = 5000;

    //并发执行线程数
    public static int threadTotal = 200;

//    private static List<Integer> list = new ArrayList<>();

//    private static Set<Integer> set = new HashSet<>();

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception{

        //1. 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //2. 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);
    }
}