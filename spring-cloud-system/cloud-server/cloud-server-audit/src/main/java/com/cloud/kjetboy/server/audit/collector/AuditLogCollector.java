package com.cloud.kjetboy.server.audit.collector;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

/**
 * @author jet
 */
public interface AuditLogCollector {
    /**
     * 日志采集
     * @return
     */
    AuditLogEventData collect();
}
