package com.chatRobot.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class ThreadPrintTest {


    private IUserDao  userDao;



        // 创建变量
        int i = 1;

        public static void main(String[] args) {
            // 创建该类的对象
            ThreadPrintTest obj = new ThreadPrintTest();
            // 使用匿名内部类的形式，没创建runnable对象
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while(obj.i < 1000){
                        // 上锁当前对象
                        synchronized(this) {
                            // 唤醒另一个线程
                            notify();
                            System.out.println("Thread " + Thread.currentThread().getName()  + " "+ obj.i ++);
                            try {
                                Thread.currentThread();
                                // 使其休眠100毫秒，放大线程差异
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                // 释放掉锁
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };

            // 启动多个线程（想创建几个就创建几个）
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();

        }
    }