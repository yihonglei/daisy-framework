package com.jpeony.boot.core.service.delay;

import com.jpeony.boot.common.concurrent.DelayQueueManager;
import com.jpeony.boot.core.service.DelayService;
import com.jpeony.boot.core.worker.DelayTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yihonglei
 */
@Service
public class DelayServiceImpl implements DelayService {

    private Logger logger = LoggerFactory.getLogger(DelayServiceImpl.class);
    
    private DelayQueueManager delayQueueManager = DelayQueueManager.getInstance();

    @Override
    public void delayTask(long passTime, Date startTime) {
        logger.info("测试延时任务，passTime={}", passTime);
        logger.info("写你的业务代码......");
        // 判断过去多久了
        passTime = (System.currentTimeMillis() - startTime.getTime()) / 1000;
        if (passTime > 20) {
            logger.info("任务结束!");
            return;
        }
        // 加入延时队列
        DelayTaskWorker delayTaskWorker = new DelayTaskWorker(passTime, startTime);
        delayQueueManager.put(delayTaskWorker, 5, TimeUnit.SECONDS);
    }
}
