package com.jpeony.boot.core.service;

import java.util.Date;

public interface DelayService {
    /**
     * @param passTime  过去时间
     * @param startTime 开始时间
     */
    void delayTask(long passTime, Date startTime);
}
