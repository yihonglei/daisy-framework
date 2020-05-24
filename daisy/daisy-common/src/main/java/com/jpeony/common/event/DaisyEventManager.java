package com.jpeony.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Vector;

/**
 * 事件管理器
 *
 * @author yihonglei
 */
public class DaisyEventManager {
    private static Logger logger = LoggerFactory.getLogger(DaisyEventManager.class);
    private static EnumMap<DaisyEventEnum, Vector<DaisyEventListener>> eventListeners = new EnumMap<>(DaisyEventEnum.class);

    public static void addListener(DaisyEventEnum event, DaisyEventListener el) {
        Vector<DaisyEventListener> listeners = eventListeners.get(event);
        if (listeners == null) {
            listeners = new Vector<>();
            eventListeners.put(event, listeners);
        } else {
            for (DaisyEventListener l : listeners) {
                if (l.getClass() == el.getClass()) {
                    logger.info("more than one instances added, this class is {}", l.getClass());
                    return;
                }
            }
        }
        listeners.addElement(el);
    }

    /**
     * 移出指定事件的指定监听器
     *
     * @author yihonglei
     */
    public static synchronized void removeListener(DaisyEventEnum event, DaisyEventListener el) {
        Vector<DaisyEventListener> listeners = eventListeners.get(event);
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
        Vector<DaisyEventListener> listeners = eventListeners.get(event);
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
        Vector<DaisyEventListener> listeners = eventListeners.get(obj.getEvent());
        if (listeners == null) {
            return;
        }
        for (DaisyEventListener listener : listeners) {
            listener.fire(obj);
        }
    }
}
