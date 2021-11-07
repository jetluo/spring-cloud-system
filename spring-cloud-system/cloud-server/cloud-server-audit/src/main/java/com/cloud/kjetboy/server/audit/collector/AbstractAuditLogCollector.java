package com.cloud.kjetboy.server.audit.collector;


import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.logger.AuditLogEventHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jet
 */
public abstract class AbstractAuditLogCollector implements AuditLogCollector {
    protected Logger logger = LoggerFactory.getLogger(AbstractAuditLogCollector.class);

    @Override
    public AuditLogEventData collect() {
        AuditLogEventData auditLogEventData = getAuditLogEventData();
        if (auditLogEventData != null) {
            auditLogEventData = wrapAuditLogEventData(auditLogEventData);
        }
        return auditLogEventData;
    }

    protected AuditLogEventData getAuditLogEventData() {
        return AuditLogEventHolder.get();
    }

    protected AuditLogEventData wrapAuditLogEventData(AuditLogEventData auditLogEventData) {
        return auditLogEventData;
    }
}
