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
public class RoseEventManager {
    private static Logger logger = LoggerFactory.getLogger(RoseEventManager.class);
    private static EnumMap<RoseEventEnum, Vector<RoseEventListener>> eventListeners = new EnumMap<>(RoseEventEnum.class);

    public static void addListener(RoseEventEnum event, RoseEventListener el) {
        Vector<RoseEventListener> listeners = eventListeners.get(event);
        if (listeners == null) {
            listeners = new Vector<>();
            eventListeners.put(event, listeners);
        } else {
            for (RoseEventListener l : listeners) {
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
    public static synchronized void removeListener(RoseEventEnum event, RoseEventListener el) {
        Vector<RoseEventListener> listeners = eventListeners.get(event);
        if (listeners != null) {
            listeners.remove(el);
        }
    }

    /**
     * 清除指定事件的所有监听器
     *
     * @author yihonglei
     */
    public static void purgeListeners(RoseEventEnum event) {
        Vector<RoseEventListener> listeners = eventListeners.get(event);
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
    public static void fireEvent(RoseEvent obj) {
        Vector<RoseEventListener> listeners = eventListeners.get(obj.getEvent());
        if (listeners == null) {
            return;
        }
        for (RoseEventListener l : listeners) {
            l.fire(obj);
        }
    }
}
