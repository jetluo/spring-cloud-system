package com.cloud.kjetboy.server.audit.collector;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import org.apache.commons.lang3.StringUtils;

public class CurrentPrincipalAuditLogCollector extends AbstractAuditLogCollector {
    @Override
    protected AuditLogEventData wrapAuditLogEventData(AuditLogEventData auditLogEventData) {
        auditLogEventData.setUserName(getUserName());
        return super.wrapAuditLogEventData(auditLogEventData);
    }

    public static String getUserName() {
        String name = "a";// (String) CurrentPrincipalHolder.getAttribute("name");
        String username = "a";//(String) CurrentPrincipalHolder.getAttribute("username");
        String userAuditName = "";
        if (StringUtils.isNotBlank(name)) {
            userAuditName = name;
        }
        if (StringUtils.isNotBlank(username)) {
            userAuditName = username;
        }
        return userAuditName;
    }
}
