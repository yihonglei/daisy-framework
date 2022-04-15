package com.jpeony.boot.core.service.cpu;

import com.jpeony.boot.core.service.CPUDemo2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 业务接口实现
 *
 * @author yihonglei
 */
@Slf4j
@Service
public class CPUDemo2ServiceImpl implements CPUDemo2Service {
    @Override
    public void demo(String bizContext) {
        // 业务处理
        log.info("测试无返回值工作线程!bizContext={}", bizContext);
    }
}
