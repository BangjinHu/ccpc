package com.ccpc.edu.xidian.cn.ccpc.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    private static int THREAD_COUNT = 30;

    private static ExecutorService executorService = Executors.newScheduledThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < THREAD_COUNT; i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    if (semaphore.tryAcquire()){//尝试获取一个许可
                        test(threadNum);
                        semaphore.release();//释放许可
                    }
//                    semaphore.acquire(3);//获取许可
//                    System.out.println("save data");
//                    semaphore.release(3);//释放许可
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(1000);

    }

}
