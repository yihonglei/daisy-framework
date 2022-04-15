package com.jpeony.boot.test.service.worker;

import com.jpeony.boot.common.utils.ThreadUtils;
import com.jpeony.boot.core.service.DelayService;
import com.jpeony.boot.core.worker.CPUDemo2Worker;
import com.jpeony.boot.core.worker.CPUDemo1Worker;
import com.jpeony.boot.core.worker.IODemoWorker;
import com.jpeony.boot.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * 测试下线程
 *
 * @author yihonglei
 */
@Slf4j
public class WorkerTest extends BaseServletTest {
    @Autowired
    private DelayService delayService;

    /**
     * CPU密集，有返回值
     */
    @Test
    public void testCPUDemo1Worker() throws ExecutionException, InterruptedException {
        String bizContext = "CPU密集型";
        Integer result = ThreadUtils.submit(new CPUDemo1Worker(bizContext)).get();
        log.info("result={}", result);
    }

    /**
     * CPU密集，无返回值
     */
    @Test
    public void testCPUDemo2Worker() {
        String bizContext = "CPU密集型";
        ThreadUtils.execute(new CPUDemo2Worker(bizContext));
    }

    /**
     * IO密集，无有返回值
     */
    @Test
    public void testIODemoWorker() throws ExecutionException, InterruptedException {
        String bizContext = "IO密集型";
        ThreadUtils.executeStandard(new IODemoWorker(bizContext));
    }

    /**
     * 延时任务测试
     */
    @Test
    public void testDelayTask() {
        delayService.delayTask(0, new Date());
        sleep();
    }

    /**
     * 休眠，保持服务存活
     */
    public void sleep() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
