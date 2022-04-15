package com.jpeony.boot.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 事件管理器
 *
 * @author yihonglei
 */
public class DaisyEventManager {
    private static Logger logger = LoggerFactory.getLogger(DaisyEventManager.class);
    private static EnumMap<DaisyEventEnum, CopyOnWriteArrayList<DaisyEventListener>> eventListeners = new EnumMap<>(DaisyEventEnum.class);

    public static void addListener(DaisyEventEnum event, DaisyEventListener el) {
        CopyOnWriteArrayList<DaisyEventListener> listeners = eventListeners.get(event);
        if (listeners == null) {
            listeners = new CopyOnWriteArrayList<>();
            eventListeners.put(event, listeners);
        } else {
            for (DaisyEventListener listener : listeners) {
                if (listener.getClass() == el.getClass()) {
                    logger.info("more than one instances added, this class is {}", listener.getClass());
                    return;
                }
            }
        }
        listeners.add(el);
    }

    /**
     * 移出指定事件的指定监听器
     *
     * @author yihonglei
     */
    public static synchronized void removeListener(DaisyEventEnum event, DaisyEventListener el) {
        CopyOnWriteArrayList<DaisyEventListener> listeners = eventListeners.get(event);
        if (listeners != null) {
            listeners.remove(el);
        }
    }

    /**
     * 清除指定事件的所有监听器
     *
     * @author yihonglei
     */
    public static void purgeListeners(DaisyEventEnum event) {
        CopyOnWriteArrayList<DaisyEventListener> listeners = eventListeners.get(event);
        if (listeners != null) {
            listeners.clear();
            // 移除该事件
            eventListeners.remove(event);
        }
    }

    /**
     * 触发事件
     *
     * @author yihonglei
     */
    public static void fireEvent(DaisyEvent obj) {
        CopyOnWriteArrayList<DaisyEventListener> listeners = eventListeners.get(obj.getEvent());
        if (listeners == null) {
            return;
        }
        for (DaisyEventListener listener : listeners) {
            listener.fire(obj);
        }
    }
}
