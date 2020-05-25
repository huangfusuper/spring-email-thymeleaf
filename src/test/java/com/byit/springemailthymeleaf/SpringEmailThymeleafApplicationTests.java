package com.byit.springemailthymeleaf;

import com.byit.springemailthymeleaf.utils.RedisLockUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringEmailThymeleafApplicationTests {

    @Test
    public void lock() throws InterruptedException {
        boolean huangfu = RedisLockUtil.tryLock("huangfu");
        if(huangfu) {
            System.out.println("------线程1拿到锁了---------");
            Thread.sleep(60*1000);
            RedisLockUtil.unlock("huangfu");
        }else {
            System.out.println("失败");
        }
    }

    @Test
    public void lock2() throws InterruptedException {
        boolean huangfu = false;
        while (!huangfu) {
            System.out.println("----------------获取锁失败--------------");
            Thread.sleep(1000);
            huangfu = RedisLockUtil.tryLock("huangfu",30);

        }
        System.out.println("------线程2拿到锁了---------");
        RedisLockUtil.unlock("huangfu");
    }


}
