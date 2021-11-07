package com.cloud.kjetboy.server.audit.entity;

import com.cloud.kjetboy.server.audit.logger.AuditLogger;

public class Audit {
    private AuditLogger logger;

    public AuditLogger getLogger() {
        return this.logger;
    }

    public void setLogger(AuditLogger logger) {
        this.logger = logger;
    }

    public void send(String message, Object... args) {
        this.logger.send(message, args);
    }

    public void send(AuditLogEventData auditLogEventData) {
        this.logger.send(auditLogEventData);
    }

    public boolean checkServerStatus() {
        return this.logger.checkServerStatus();
    }
}
