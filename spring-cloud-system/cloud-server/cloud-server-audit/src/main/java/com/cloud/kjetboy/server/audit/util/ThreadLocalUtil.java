package com.cloud.kjetboy.server.audit.util;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.logger.AuditLogEventHolder;

/**
 * @author jet
 */
public class ThreadLocalUtil {
    public static AuditLogEventData get() {
        return AuditLogEventHolder.get();
    }

    public static void set(AuditLogEventData value) {
        AuditLogEventHolder.set(value);
    }

    public static void removeAll() {
        AuditLogEventHolder.removeAll();
    }
}

