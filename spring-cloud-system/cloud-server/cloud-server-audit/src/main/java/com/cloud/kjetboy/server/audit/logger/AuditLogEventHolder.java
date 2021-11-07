package com.cloud.kjetboy.server.audit.logger;

import com.cloud.kjetboy.server.audit.entity.AuditInfo;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

/**
 * @author jet
 */
public class AuditLogEventHolder {
    private static NamedThreadLocalWrapper<AuditLogEventData> threadLocal = new NamedThreadLocalWrapper("audit ThreadLocal");

    public static AuditLogEventData get() {
        return (AuditLogEventData)threadLocal.get();
    }

    public static void set(AuditLogEventData value) {
        threadLocal.set(value);
    }

    public static void removeAll() {
        threadLocal.remove();
    }

    public static void setObjectInfo(String objectId, String objectName) {
        AuditLogEventData data = get();
        if (data != null) {
            data.addCommonAttribute("objectId", objectId);
            data.addCommonAttribute("objectName", objectName);
        }
    }

    public static void addCommonAttribute(String key, String value) {
        AuditLogEventData data = get();
        if (data != null){
            data.addCommonAttribute(key, value);}
    }

    public static void addMessageTemplate(String template) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setMessageTemplate(template);
        }
    }
    public static void defaultAuditItem() {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setDefaultAuditItem(true);
        }
    }

    public static void addOptType(String optType) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setOptType(optType);
        }
    }

    public static void addEventLevel(String eventLevel) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setEventLevel(eventLevel);
        }
    }

    public static void addEventType(String eventType) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo AuditInfo = getSafeAuditInfo(data);
            AuditInfo.setEventType(eventType);
        }
    }

    public static void addReportAnalyze(String reportAnalyze) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setAnalysis(reportAnalyze);
        }
    }

    public static void addCategoryName(String categoryName) {
        AuditLogEventData data = get();
        if (data != null) {
            AuditInfo auditInfo = getSafeAuditInfo(data);
            auditInfo.setCategoryName(categoryName);
        }
    }

    public static AuditInfo getSafeAuditInfo(AuditLogEventData data) {
        AuditInfo auditInfo = data.getAuditInfo();
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
            data.setAuditInfo(auditInfo);
        }
        return auditInfo;
    }
}
