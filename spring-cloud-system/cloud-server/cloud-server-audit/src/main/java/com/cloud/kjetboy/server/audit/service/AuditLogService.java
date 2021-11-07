package com.cloud.kjetboy.server.audit.service;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

/**
 * @author jet
 */
public interface AuditLogService {

    /**
     * @param paramAuditLogEventData
     */
    void saveLog(AuditLogEventData paramAuditLogEventData);
}
