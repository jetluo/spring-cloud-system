package com.cloud.kjetboy.server.audit.collector;

/**
 * @author jet
 */
public interface OutputResultResolver {
    /**
     * 参数解析
     * @param paramObject
     * @return
     */
    String resolve(Object paramObject);
}
