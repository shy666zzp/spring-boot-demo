package com.xkcoding.async.task;

import com.xkcoding.async.SpringBootDemoAsyncApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 * 测试任务
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-29 10:49
 */
@Slf4j
public class TaskFactoryTest extends SpringBootDemoAsyncApplicationTests {
    @Autowired
    private TaskFactory task;

    /**
     * 测试异步任务
     *
     * 方法逻辑：
     * 1. 同时启动三个异步任务
     * 2. 通过阻塞调用等待所有任务完成
     * 3. 计算并记录总执行时间
     *
     * @throws InterruptedException 当线程在阻塞等待时被中断抛出（来自Future.get()）
     * @throws ExecutionException 当异步任务执行过程中发生异常时抛出（来自Future.get()）
     */
    @Test
    public void asyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        // 同时启动三个异步任务（并行执行）
        Future<Boolean> asyncTask1 = task.asyncTask1();
        Future<Boolean> asyncTask2 = task.asyncTask2();
        Future<Boolean> asyncTask3 = task.asyncTask3();

        // 通过阻塞获取结果的方式等待所有任务完成
        // 注意：此处顺序调用get()会依次阻塞主线程
        asyncTask1.get();
        asyncTask2.get();
        asyncTask3.get();
        long end = System.currentTimeMillis();

        // 记录从任务启动到全部完成的耗时（包含任务调度和线程切换开销）
        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }


    /**
     * 测试同步任务
     */
    @Test
    public void taskTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }
}
