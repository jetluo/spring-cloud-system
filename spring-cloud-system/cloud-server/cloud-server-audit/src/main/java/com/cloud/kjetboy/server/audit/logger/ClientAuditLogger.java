package com.cloud.kjetboy.server.audit.logger;

import com.alibaba.fastjson.JSON;
import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.service.AuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author jet
 */
public class ClientAuditLogger extends DefaultAsyncAuditLogger {
    private static Logger logger = LoggerFactory.getLogger(ClientAuditLogger.class);
    @Autowired
    private AuditLogService auditLogService;

    public ClientAuditLogger(AuditProperties auditLogClientConfig) {
        this.auditProperties = auditLogClientConfig;
    }

    public ClientAuditLogger(AuditProperties auditLogClientConfig, RestTemplate restTemplate) {
        this.auditProperties = auditLogClientConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    protected void sendJSON(Object json) {
        //添加数据写入
        auditLogService.saveLog((AuditLogEventData) json);
        logger.info("ClientAuditLogger发送json数据：" + JSON.toJSONString(json));
    }
}
