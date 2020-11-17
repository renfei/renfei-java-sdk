package net.renfei.sdk.test;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title: Tests</p>
 * <p>Description: 单元测试</p>
 *
 * @author RenFei
 * @date : 2020-08-24 21:59
 */
public class Tests {
    /**
     * 使用多线程并发执行测试，检测线程安全
     *
     * @param threadNums 并发数量
     * @param task       任务
     * @throws InterruptedException
     */
    protected void startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
    }
}
