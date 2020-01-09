package com.jpeony.common.event;

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
public class RoseEvent extends EventObject {
    RoseEventEnum event;
    Object[] args;

    /**
     * 事件对象
     *
     * @param roseEventEnum 事件类型
     * @param source        触发事件初始化对象
     * @param args          其他参数
     * @author yihonglei
     */
    public RoseEvent(RoseEventEnum roseEventEnum, Object source, Object... args) {
        super(source);
        this.event = roseEventEnum;
        this.args = args;
    }

    /**
     * 事件对象
     *
     * @param roseEventEnum 事件
     * @param source        触发事件初始化对象
     * @author yihonglei
     */
    public RoseEvent(RoseEventEnum roseEventEnum, Object source) {
        this(roseEventEnum, source, null);
    }
}
