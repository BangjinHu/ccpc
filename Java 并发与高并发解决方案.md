## Java 并发与高并发解决方案

[TOC]

### 线程

3种使用线程的方法：

    1.实现Runnable接口
    
    2.实现Callable接口
    
    3.继承Thread类
    
>提示：实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。可以说任务是通过线程驱动从而执行的。

>>注意：实现接口和继承之间，实现接口会好一些。Java不支持多重继承，因此继承了Thread类就无法继承其它类，但是可以实现多个接口。类可能只要求可执行就行，继承整个Thread类开销大。

### 并发

多个线程操作相同的资源，保证线程安全，合理使用资源。

### 高并发

高并发是互联网分布式系统架构设计中必须考虑的因素之一，它通常指通过设计保证系统能够同时并行处理很多请求。

服务能够同时处理很多请求，提高程序性能。


### CPU多级缓存

CPU的频率太快，快到主存跟不上，这样在处理器时钟周期内，CPU经常需要等待主存，浪费资源。所以cache的出现，是为了解决CPU与内存之间速度不匹配的问题。

#### 1. CPU cache的意义

    局部性原理：
    
        1. 时间局部性： 如果某个数据被访问，那么在不久的将来它很可能被再次访问。
        
        2. 空间局部性： 如果某个数据被访问，那么与它相邻的数据很快也可能被访问。
    
#### 2. CPU多级缓存 - 缓存一致性（MESI）

MESI目的用于保证多个CPU Cache之间缓存共享数据的一致性。

    MESI 四种状态各自代表的含义
    
    四种操作：
    
        1. local read
        
        2. local write
        
        3. remote read
        
        4. remote write

#### 3. CPU多级缓存 - 乱序执行优化

乱序执行优化，表示处理器为了提高运算速度而做出违背代码原则原有顺序的优化。

#### 4. Java内存模型(JMM)

    1. JMM规定
    
    
    2. 抽象结构
    
    
    3. 同步八种操作及规则
    
        1. lock（锁定），作用于主内存的变量，将一个变量标识为一条线程独占状态
        
        2. unlock（解锁），作用于主内存的变量，将一个处于锁定状态的变量释放出来，释放后的变量才可以被其它线程锁定
        
        3. read（读取），作用于主内存的变量，将一个变量值从主内存传输到线程的工作内存中，以便随后的load动作使用
        
        4. load（载入），作用于工作内存的变量，将read操作从主内存中得到的变量值放入工作内存的变量副本中
        
        5. use（使用），作用于工作内存的变量，将工作内存中的一个变量值传递给执行引擎
        
        6. assign（赋值），作用于工作内存的变量，将一个从执行引擎接收到的值赋值给工作内存中的变量
        
        7. store（存储），作用于工作内存的变量，将工作内存中的一个变量的值传送到主内存中，以便随后的write操作
        
        8. write（写入），作用于主内存的变量，将store操作从工作内存中一个变量的值传送到主内存的变量中
        
        ---
        
        1. ...
    
    

### 并发的优势和风险

#### 1. 优势

    1. 速度
    
        同时处理多个请求，响应更快，复杂的操作可以分成多个进程同时进行
        
    2. 设计
    
        程序设计在某些情况下更简单，也可以有更多的选择
        
    3. 资源利用
    
        CPU能够在等待IO的时候做一些其它事情
        

#### 2. 风险

    1. 安全性
    
        多个线程共享数据时可能会产生于期望不相符的结果
        
    2. 活跃性
    
        某个操作无法继续进行下去时，就会发生活跃性问题，如死锁、饥饿等问题。
        
    3. 性能
    
        线程过多时会使得CPU频繁切换、调度时间增多、同步机制、消耗过多内存
        
### 并发模拟

    1. Postman HTTP请求工具
    
    2. Apache Bench（AB）Apache附带工具-测试网站性能
    
    3. JMeter Apache组织开发的压力测试工具
    
        测试工具建议使用Apache-JMeter
    
    4. Semaphore | CountDownLatch等


### 线程安全性

在多个线程访问某个类时，不管运行的环境采用何种调度方式或这些进程如何交替执行，并且在主调代码中不需要任何额外的同步或协同，这个类都表现出正确的行为，所以这类是线程安全的。

#### 1. 原子性

提供互斥访问，同一时刻只能有一个线程对它进行操作
        
    1. Atomic包
    
        AtomicXXX:CAS | Unsafe.compareAndSwapInt
        
            AtomicLong | LongAdder
            
    2. 锁

        1. synchronized 依赖于JVM
        
            1. 修饰代码块：大括号括起来的代码，作用于调用的对象
            
            2. 修饰方法：整个方法，作用于调用的对象
            
            3. 修饰静态方法：整个静态方法，作用于所有对象
            
            4. 修饰类：括号括起来的部分，作用于所有对象
        
        2. Lock 依赖特殊的CPU指令，代码实现，ReentrantLock
    
>提示：synchronized是不可中断锁，适合竞争不激烈可读性好的业务场景。Lock是可中断锁，多样化同步，竞争激烈时能够维持常态。Atomic竞争激烈时维持常态，比Lock性能好，只能同步一个值。

#### 2. 可见性
    
一个线程对主内存的修改可以及时的被其它线程观察到。

导致线程共享变量在线程间不可见的原因：1. 线程交叉执行。2.重排序组合线程交叉执行。3.共享变量更新后的值没有在工作内存与主内存间及时更新。

>JMM关于synchronized的两条规定：
    
    1. 线程解锁前，必须将共享变量的最新值刷新到主内存
    
    2. 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（提示：加锁与解锁是同一把锁）。
    
>volatitle

    通过加入内存屏障和禁止重排序优化来实现。
    
        1. 对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中共享变量值刷新到主内存。
        
        2. 对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量。
        
        注意：volatile的的操作：1. 对变量的写操作不影响当前值。2. 
        
        
#### 3. 有序性
    
一个线程观察其它线程中的指令执行顺序，由于指令重排序的存在，该结果一般杂乱无序。

Java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响单线程程序的执行，却会影响到多线程并发执行的正确性。

在并发操作过程中，可以通过 volatile | synchronized | Lock 关键字避免编译器和处理器对指令进行重排序。

>Happens - Before原则

    1. 程序次序原则，一个线程内，按照代码顺序，书写在前面的操作会先发生书写在后面的操作。（单线程内）
    
    2. 锁定规则，一个unlock操作先发生于后面对同一个锁的lock操作。（不论是单线程或多线程）
    
    3. volatile变量规则，对一个变量的写操作先行发生于后面对这个变量的读操作。
    
    4. 传递规则，如果操作A先发生于操作B，而操作B又先发生于操作C，则可以得出操作A先发生于操作C。
    
    5. 线程启动规则，Thread对象的start()方法先发生于此线程的每个动作。
    
    6. 线程中断规则，对线程的interrupt()方法的调用先发生于被中断线程的代码检测到中断事件的发生。
    
    7. 线程终结规则，线程中所有的操作都先发生于线程的终止检测，可以通过Thread.join()方法结束，Thread.isAlive()的返回值手段检测到线程已经终止。
    
    8. 对象终结规则，一个对象的初始化完成先发生于它的finalize()方法的开始。
    
#### 4. 线程安全性 - 总结

原子性：Atomic包 | CAS算法 | synchronized | Lock

可见性：synchronized | volatile

有序性：happens-before原则
    
---

### 安全发布对象

发布对象，指使一个对象能够被当前范围之外的代码使用。

    package com.ccpc.edu.xidian.cn.ccpc.example.publish;

    import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
    import lombok.extern.slf4j.Slf4j;
    
    import java.util.Arrays;
    
    @Slf4j
    @NotThreadSafe
    public class UnsafePublish {
    
        private String[] states = {"a", "b", "c"};
    
        public String[] getStates(){
            return states;
        }
    
        public static void main(String[] args) {
            UnsafePublish unsafePublish = new UnsafePublish();
            log.info("{}", Arrays.toString(unsafePublish.getStates()));
    
            unsafePublish.getStates()[0] = "d";
            log.info("{}", Arrays.toString(unsafePublish.getStates()));
    
        }
    }


对象溢出，指一种错误的发布方式，当一个对象还没有构造完成时，就使它被其它线程所见。

    package com.ccpc.edu.xidian.cn.ccpc.example.publish;

    import com.ccpc.edu.xidian.cn.ccpc.annoations.NotRecommend;
    import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
    import lombok.extern.slf4j.Slf4j;
    
    @Slf4j
    @NotThreadSafe
    @NotRecommend
    public class Escape {
    
        private int thisCanBeEscape = 0;
    
        public Escape(){
            //还未构建完成就发布
            new InnerClass();
        }
    
        private class InnerClass {
            public InnerClass(){
                log.info("{}", Escape.this.thisCanBeEscape);
            }
        }
    
        public static void main(String[] args) {
            new Escape();
        }
    }

#### 1. 安全发布对象方法

    1. 在静态初始化函数中初始化一个对象引用
    
    2. 将对象的引用保存到volatile类型域或者AtomicRefernce对象中
    
    3. 将对象的引用保存到某个正确构造对象的final类型域中
    
    4. 将对象的引用保存到一个由锁保护的域中
    

### 线程安全策略

#### 1. 不可变对象

    不可变对象满足的条件：
    
        1. 对象创建以后其状态不能修改
        
        2. 对象所有域都是final类型
        
        3. 对象是正确创建的（在对象创建期间，this引用没有溢出）
        
    final关键字
    
        修饰类：不能被继承
        
        修饰方法：效率、锁定方法不能被继承类修改
        
        修饰变量：基本数据类型变量、引用类型变量
        
    效果类似final的方法（Java 8 提供）
    
        1. Collections.unmodifiableXXX:Collectino List Set Map
        
            package com.ccpc.edu.xidian.cn.ccpc.example.immutable;

            import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;
            import com.google.common.collect.Maps;
            import lombok.extern.slf4j.Slf4j;
            
            import java.util.Collections;
            import java.util.Map;
            
            @Slf4j
            @ThreadSafe
            public class ImmutableExample3 {
                private static Map<Integer, Integer> map = Maps.newHashMap();
            
                static {
                    map.put(1, 3);
                    map.put(2, 4);
                    map.put(3, 5);
                    map = Collections.unmodifiableMap(map);
                }
            
                public static void main(String[] args) {
                    map.put(1, 4);
                    log.info("{}", map.get(1));
                }
            }

        
        2. Guava：ImmutableXXX:Collection List Set Map ..
        
>总结：在使用的时候尽可能的将一些对象变成不可变对象，那么就不会产生线程不安全问题。

#### 2. 线程封闭

    1. Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
    
    2. 堆栈封闭：局部变量，无并发问题
    
    3. ThreadLocal线程封闭：很好的封闭方法
    
### 线程不安全类

    1. StringBuilder | StringBuffer 
        
        在多线程环境中使用StringBuffer，是为了安全。但是在不那么在意线程执行安全的时候考虑到上下文切换的开销，那么使用StringBuilder会更有效一些。
    
    2. SimpleDateFormat | JodaTime
    
    3. ArrayList | HashSet | HashMap 等Collections
    
    4. 先检查再执行（if(condition(a)){handle(a);}）//线程非安全
    
    
### 线程安全类 - 同步容器

    1. ArrayList -> Vector | Stack
    
    2. HashMap -> Hashtable(key|value不能为null)
    
    3. Collections.synchronizedXXX(List | Set | Map)

    
### 线程安全 - 并发容器 J.U.C

    1. ArrayList -> CopyOnWriteArrayList
    
    2. HashSet | TreeSet -> CopyOnWriteArraySet | ConcurrentSkipListSet
    
    3. HashMap | TreeMap -> ConcurrentHashMap | ConcurrentSkipListMap
    
### 安全共享对象策略 - 总结

    1. 线程限制：一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改
    
    2. 共享只读：一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它
    
    3. 线程安全对象：一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其它线程无需额外的同步就可以通过公共接口随意访问它
    
    4. 被守护的线程：被守护的线程只能通过获取特定的锁来访问
    

---

### AQS

1. 使用Node实现FIFO队列，可以用于构建锁或者其它同步装置的基础框架。

2. 利用一个int类型表示状态

3. 使用方法是继承

4. 子类通过继承并通过实现它的方法管理其它状态（acquire和release）的方法操作状态

5. 可以同时实现排它锁和共享锁模式（独占、共享）


#### 1. JUC - AQS同步组件

##### 1. CountDownLatch


##### 2. Semaphore


##### 3. CyclicBarrier



##### 4. ReentrantLock 与 锁

    1. ReentrantLock | synchronized 之间的区别
    
        1. 可重入性
        
        2. 锁的实现
        
        3. 性能的区别
        
        4. 功能的区别
        
        
        注意：ReentrantLock独有功能
        
            1. 可指定是公平锁还是非公平锁
            
            2. 提供了一个Condition类，可以分组唤醒需要唤醒的线程
            
            3. 提供能够中断等待锁的线程机制，lock.lockInterruptibly()
            
            

#### 2. JUC - FutureTask

##### 1. Callable与Runnable接口对比

Callable是一个泛型，其中有一个call()方法，有返回值。



#### 3. Fork | Join



#### 4. BlockingQueue 阻塞队列

该情况用于生产消费模式。

阻塞队列给出4套方法
- | Throws Exception | Special Value | Blocks | Times Out 
--|--|--|--|--
insert | add(o) | offer(o) | put(o) | offer(o, timeout, timeout)
remove | remove(o) | poll(o) | take() | poll(timeout, timeout) 
examine | element() | peek() |        | 

- ArrayBlockingQueue 
- DelayQueue
- LinkedBlockingQueue
- PriorityBlockingQueue
- SynchronousQueue



#### 5. Condition


---

### 线程池

    1. new Thread 弊端
    
        1. 每次都需要new Thread新建对象，性能较差
        
        2. 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多系统资源导致死机或OOM
        
        3. 缺少更多的功能。如：更多执行、定期执行、线程中断
        
    2. 线程池的优势
    
        1. 重用存在的线程，减少对象创建、消亡的开销，性能优
        
        2. 可以有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞
        
        3. 线程池可以提供定时执行、定期执行、单线程、并发数控制等功能
        
#### 1. ThreadPoolExecutor

    1. 参数
    
        1. corePoolSize 核心线程数量
        
        2. maximumPoolSize 线程最大线程数
        
        3. workQueue 阻塞队列，存储等待执行的任务（重要），会对线程池运行过程产生很大影响
        
        4. keepAliveTime 线程没有任务执行时最多保持多久时间终止
        
        5. unit keepAliveTime的时间单位
        
        6. threadFactory 线程工厂，用来创建线程
        
        7. rejectHandler 当拒绝处理任务时的策略
        
            源码
        
    2. 方法
    
        1. execute() 提交任务，交给线程池执行
        
        2. submit() 提交任务，能够返回执行结果 execute+Future
        
        3. shutdown() 关闭线程池，等待任务被执行完
        
        4. shutdownNow() 关闭线程池，不等待任务执行完
        
        5. getTaskCount() 线程池已经执行或未执行的任务总数
        
        6. getCompletedTaskCount() 已完成的任务数量
        
        7. getPoolSize() 线程池当前的线程数量
        
        8. getActiveCount 当前线程池中正在执行任务的线程数量
        
        9. 
        
#### 2. Executor框架接口

    1. Executors.newCachedThreadPool
    
    2. Executors.newFixedThreadPool
    
    3. Executors.newScheduledThreadPool
    
    4. Executors.newSingleThreadExecutor
    
#### 3. 线程池 - 合理配置

    1. CPU密集型任务需要尽量压榨CPU，参考值可以设置为NCPU+1
    
    2. IO密集型任务，参考值可以设置为2*NCPU
    
    3. 
    
    
### 死锁

#### 1. 死锁必要条件

    1. 互斥条件
    
    2. 请求和保持条件
    
    3. 不剥夺条件
    
    4. 环路等待条件
    
---

### 高并发处理

#### 高并发处理思路手段

    1. 扩容
    
        1. 垂直扩容，提高系统部件的能力（内存）
        
        2. 水平扩容，增加更多系统成员来实现（添加多台服务器）
        
    2. 扩容 - 数据库
    
        1. 读操作扩展
        
            memcache
            
            redis
            
            CDN
            
        2. 写操作扩展
        
            Cassandra
            
            Hbase
            
    3. 缓存
    
        1. 缓存特征
        
            1. 命中率
            
                命中数 / (命中数 + 没有命中数)
                
            2. 最大元素（空间）
            
            3. 清空策略
            
                FIFO（先进先出策略） LFU（最早使用策略） LRU 过期时间 随机等。
                
        2. Guava Cache
        
        
        3. Memcache
        
        4. Redis 
    
#### 高并发场景下缓存常见问题

    1. 缓存一致性
    
    
    2. 缓存并发问题
    
    
    
    3. 缓存穿透问题
    
    
    
    4. 缓存的雪崩现象
    
    
    
#### 消息队列 Kafka RabbitMQ




### 应用拆分



