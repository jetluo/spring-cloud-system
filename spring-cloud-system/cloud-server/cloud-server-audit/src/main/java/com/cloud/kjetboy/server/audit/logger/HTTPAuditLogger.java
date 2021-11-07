package com.cloud.kjetboy.server.audit.logger;

import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.util.ICallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author jet
 */
public class HTTPAuditLogger extends DefaultAsyncAuditLogger {
    public HTTPAuditLogger(AuditProperties auditLogClientConfig, RestTemplate restTemplate) {
        this.auditProperties = auditLogClientConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    protected void sendJSON(Object json) {
        System.out.println("HTTPAuditLogger发送json结果："+ json.toString());
        logger.debug("HTTPAuditLogger发送json结果："+ json);
//        if (this.auditProperties.isHeartbeatCheck()) {
//            ResponseEntity<String> responseEntity = doPost(this.auditProperties.getReceiveServerUrl(), json,
//                    LoggingCallBack.getCallBack());
//            if (responseEntity == null && StringUtils.isNotBlank(this.auditProperties
//                    .getAlternativeReceiveServerUrl())) {
//                logger.debug(");
//                        doPost(this.auditLogClientProperties.getAlternativeReceiveServerUrl(), json,
//                                LoggingCallBack.getCallBack());
//            }
//            return;
//        }
//        if (this.auditProperties.isAsync()) {
//            doAsync(this.auditProperties.getReceiveServerUrl(), json, LoggingCallBack.getCallBack());
//        } else {
//            ResponseEntity<String> responseEntity = doPost(this.auditProperties.getReceiveServerUrl(), json,
//                    LoggingCallBack.getCallBack());
//            if (responseEntity == null && StringUtils.isNotBlank(this.auditProperties
//                    .getAlternativeReceiveServerUrl())) {
//                logger.debug("");
//                        doPost(this.auditProperties.getAlternativeReceiveServerUrl(), json,
//                                LoggingCallBack.getCallBack());
//            }
//        }
    }

    private static class LoggingCallBack implements ICallBack {
        private static Logger logger = LoggerFactory.getLogger(HTTPAuditLogger.class);

        private static LoggingCallBack loggingCallBack = new LoggingCallBack();

        public static ICallBack getCallBack() {
            return loggingCallBack;
        }

        @Override
        public void callback(ResponseEntity<String> response) {
            logger.debug("发送请求结果："+ response.getStatusCode());
        }
    }
}