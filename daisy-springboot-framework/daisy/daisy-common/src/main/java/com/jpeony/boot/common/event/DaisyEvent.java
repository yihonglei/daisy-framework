package com.jpeony.boot.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EventObject;

/**
 * 自定义事件
 *
 * @author yihonglei
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DaisyEvent extends EventObject {
    DaisyEventEnum event;
    Object[] args;

    /**
     * 事件对象
     *
     * @param daisyEventEnum 事件类型
     * @param source         触发事件初始化对象
     * @param args           其他参数
     * @author yihonglei
     */
    public DaisyEvent(DaisyEventEnum daisyEventEnum, Object source, Object... args) {
        super(source);
        this.event = daisyEventEnum;
        this.args = args;
    }

    /**
     * 事件对象
     *
     * @param daisyEventEnum 事件
     * @param source         触发事件初始化对象
     * @author yihonglei
     */
    public DaisyEvent(DaisyEventEnum daisyEventEnum, Object source) {
        this(daisyEventEnum, source, null);
    }
}
