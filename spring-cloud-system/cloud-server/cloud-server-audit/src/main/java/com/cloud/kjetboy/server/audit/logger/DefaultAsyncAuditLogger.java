package com.cloud.kjetboy.server.audit.logger;

import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.entity.Audit;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.entity.EventType;
import com.cloud.kjetboy.server.audit.util.ICallBack;
import com.cloud.kjetboy.server.audit.util.ThreadLocalUtil;
import com.cloud.kjetboy.server.common.utils.RegexHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class DefaultAsyncAuditLogger  implements AuditLogger, InitializingBean {
    protected static Logger logger = LoggerFactory.getLogger(DefaultAsyncAuditLogger.class);

    public static final String AUDIT_LOGGERC_LASSNAME = Audit.class.getName();

    protected ExecutorService threadPool;

    protected RestTemplate restTemplate;

    protected AuditProperties auditProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        //if (this.threadPool == null && this.auditLogClientProperties.isAsync()){
            this.threadPool = Executors.newFixedThreadPool(10);
        //}

    }

    @Override
    public boolean checkServerStatus() {
//        try {
//            if (StringUtils.equals("/health", this.auditLogClientProperties.getHeartbeatCheckPath())) {
//                Map<String, Object> map = (Map<String, Object>)this.restTemplate.getForObject(this.auditLogClientProperties
//                        .getReceiveServerHeartbeatCheckUrl(), HashMap.class, new Object[0]);
//                return StringUtils.equals((String)map.get("status"), "UP");
//            }
//            String response = (String)this.restTemplate.getForObject(this.auditLogClientProperties
//                    .getReceiveServerHeartbeatCheckUrl(), String.class, new Object[0]);
//            return StringUtils.equals(response, "UP");
//        } catch (RestClientException e) {
//            logger.error(", (Throwable)RegexHelper.trick(e, null));
//        } catch (IllegalStateException e) {
//            logger.error(", (Throwable)RegexHelper.trick(e, null));
//        }
        return true;
    }

    private AuditLogEventData buildEvent(String message, Object... args) {
        AuditLogEventData auditLogEventData = ThreadLocalUtil.get();
        if (auditLogEventData == null){
            auditLogEventData = new AuditLogEventData();}
        auditLogEventData.setEventType(EventType.NORMAL);
        auditLogEventData.setStartTime(Long.valueOf(System.currentTimeMillis()));
        StackTraceElement[] steArray = (new Throwable()).getStackTrace();
        for (int i = 0; i < steArray.length; i++) {
            if (steArray[i].getClassName().equals(AUDIT_LOGGERC_LASSNAME)) {
                auditLogEventData.setClassName(steArray[i + 1].getClassName());
                auditLogEventData.setMethodName(steArray[i + 1].getMethodName());
                break;
            }
        }
        auditLogEventData.setMessageText(MessageFormatter.arrayFormat(message, args).getMessage());
        auditLogEventData.setEndTime(Long.valueOf(System.currentTimeMillis()));
        return auditLogEventData;
    }

    @Override
    public void send(String message, Object... args) {
        send(buildEvent(message, args));
    }

    @Override
    public void send(AuditLogEventData eventData) {
        if (eventData.getCommonAttribute().containsKey("hasSent")){
            return;}
        eventData.getCommonAttribute().put("hasSent", Boolean.valueOf(true));
        sendJSON(eventData);
    }

    protected void doAsync(final String url, final Object json, final ICallBack callback) {
        this.threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                ResponseEntity<String> responseData = DefaultAsyncAuditLogger.this.doPost(url, json, callback);
                responseData = (ResponseEntity<String>) RegexHelper.trick(responseData, null);
                if (responseData != null){
                    return (String)responseData.getBody();}
                return null;
            }
        });
    }

    protected ResponseEntity<String> doPost(String url, Object json, ICallBack callback) {
        try {
//            ResponseEntity<String> responseData = RestTemplateHelper.doPostExecuteEntity(this.restTemplate, url, json, String.class);
//            if (callback != null){
//                callback.callback(responseData);}
        } catch (RestClientException e) {
            logger.error("REST发送失败", (Throwable)RegexHelper.trick(e, null));
        } catch (IllegalStateException e) {
            logger.error("审计后端服务不存在", (Throwable)RegexHelper.trick(e, null));
        }
        return null;
    }

    protected abstract void sendJSON(Object paramObject);

    public void setThreadPool(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }
}
