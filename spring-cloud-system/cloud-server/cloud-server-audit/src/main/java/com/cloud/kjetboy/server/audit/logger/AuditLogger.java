package com.cloud.kjetboy.server.audit.logger;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

public interface AuditLogger {
    void send(String paramString, Object... paramVarArgs);

    void send(AuditLogEventData paramAuditLogEventData);

    boolean checkServerStatus();
}
