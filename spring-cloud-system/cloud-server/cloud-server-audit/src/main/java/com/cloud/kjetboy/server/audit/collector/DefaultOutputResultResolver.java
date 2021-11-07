package com.cloud.kjetboy.server.audit.collector;

import com.alibaba.fastjson.JSON;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.entity.RestMessage;
import com.cloud.kjetboy.server.audit.logger.AuditLogEventHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jet
 */
public class DefaultOutputResultResolver implements OutputResultResolver{

    @Override
    public String resolve(Object result) {
        if (result == null){
            return null;}
        if (result instanceof RestMessage){
            resolveRestMessage((RestMessage)result);}
        return JSON.toJSONString(result);
    }

    protected void resolveRestMessage(RestMessage restMessage) {
        if (parseCode(restMessage.getCode()) >= 400) {
            AuditLogEventData auditLogEventData = getAuditLogEventData();
            if (auditLogEventData != null) {
                auditLogEventData.setStatus(Integer.valueOf(parseCode(restMessage.getCode())));
                if (StringUtils.isNotBlank(restMessage.getMessage())) {
                    auditLogEventData.setExceptionTrace(restMessage.getMessage());
                }
            }
        }
    }

    protected int parseCode(String code) {
        try {
            return Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected AuditLogEventData getAuditLogEventData() {
        return AuditLogEventHolder.get();
    }
}
