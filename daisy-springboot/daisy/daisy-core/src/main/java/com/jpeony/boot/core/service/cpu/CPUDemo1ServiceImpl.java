package com.jpeony.boot.core.service.cpu;

import com.jpeony.boot.core.service.CPUDemo1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发送短信业务实现
 *
 * @author yihonglei
 */
@Slf4j
@Service
public class CPUDemo1ServiceImpl implements CPUDemo1Service {
    @Override
    public Integer demo(String bizContext) {
        // 业务处理
        log.info("测试有返回值工作线程!bizContext={}", bizContext);
        // 返回结果
        return 0;
    }
}
