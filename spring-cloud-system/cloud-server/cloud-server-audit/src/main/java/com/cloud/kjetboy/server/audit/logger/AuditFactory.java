package com.cloud.kjetboy.server.audit.logger;

import com.cloud.kjetboy.server.audit.entity.Audit;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

/**
 * @author jet
 */
public class AuditFactory {
    private static final Audit audit = new Audit();

    public AuditFactory(AuditLogger auditLogger) {
        audit.setLogger(auditLogger);
    }

    public static Audit getAudit() {
        return audit;
    }

    public void send(String message, Object... args) {
        getAudit().send(message, args);
    }

    public void send(AuditLogEventData auditLogEventData) {
        getAudit().send(auditLogEventData);
    }
}
