package com.cloud.kjetboy.server.audit.collector;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jet
 */
public final class AuditLogCollectors implements AuditLogCollector {
    private List<AuditLogCollector> auditLogCollectors = new ArrayList<>();

    @Override
    public AuditLogEventData collect() {
        return null;
    }

    public AuditLogEventData aggregate(AuditLogEventData auditLogEventData) {
        for (AuditLogCollector auditLogCollector : this.auditLogCollectors){
            auditLogEventData = auditLogCollector.collect();}
        return auditLogEventData;
    }

    public List<AuditLogCollector> getAuditLogCollectors() {
        return this.auditLogCollectors;
    }

    public void setAuditLogCollectors(List<AuditLogCollector> auditLogCollectors) {
        this.auditLogCollectors = auditLogCollectors;
    }
}

