package com.jpeony.boot.common.event;

import java.util.EventListener;

/**
 * 自定义事件监听，Spring的ApplicationListener也是这么干的，自定义方便自己对事件进行管理。
 * 注意，这里的事件是同步的，如果有需要，可以将事件提交到线程池异步化执行或者使用Spring的异步事件方式，
 * 它也是提交到线程池实现异步化。
 *
 * @author yihonglei
 */
public interface DaisyEventListener extends EventListener {
    /**
     * 触发事件
     *
     * @param e 相关事件
     */
    void fire(DaisyEvent e);
}
