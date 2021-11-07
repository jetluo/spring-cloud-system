package com.cloud.kjetboy.server.audit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author jet
 */
public class AuditLogEventData {
    public static final String THREAD_NAME = "threadName";

    public static final String THREAD_ID = "threadId";

    public static final String SERVICE_IP = "serviceIp";

    public static final String CLASS_NAME = "className";

    public static final String METHOD_NAME = "methodName";

    public static final String PACKAGE_NAME = "packageName";

    public static final String OBJECT_ID = "objectId";

    public static final String OBJECT_NAME = "objectName";

    public static final String OPERATION_NAME = "operationName";

    public static final String OPERATION_STATUS = "operationStatus";

    public static final String APP_NAME = "appName";

    public static final String CATEGORY = "category";

    public static final String HAS_SENT = "hasSent";

    @JsonProperty
    private Map<String, Object> commonAttribute = new LinkedHashMap<>();

    @JsonProperty
    private EventType eventType;

    @JsonProperty
    private String appCode;

    @JsonProperty
    private String appName;

    @JsonProperty
    private String appPort;

    @JsonProperty
    private String userName;

    @JsonProperty
    private String url;

    @JsonProperty
    private String ip;

    @JsonProperty
    private String method;

    @JsonProperty
    private Integer status;

    @JsonProperty
    private String className;

    @JsonProperty
    private String methodName;

    @JsonProperty
    private Long startTime;

    @JsonProperty
    private Long endTime;

    @JsonProperty
    private Long costTime;

    @JsonProperty
    private String messageText;

    @JsonProperty
    private Map<String, Object> input = new LinkedHashMap<>();

    @JsonProperty
    private String output;

    @JsonProperty
    private String exceptionTrace;

    @JsonProperty
    private boolean defaultItem;

    @JsonProperty
    private AuditInfo auditInfo;

    public boolean isDefaultItem() {
        return this.defaultItem;
    }

    public void setDefaultItem(boolean defaultItem) {
        this.defaultItem = defaultItem;
    }

    public Map<String, Object> getCommonAttribute() {
        return this.commonAttribute;
    }

    public void setCommonAttribute(Map<String, Object> commonAttribute) {
        this.commonAttribute = commonAttribute;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Map<String, Object> getInput() {
        return this.input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public String getOutput() {
        return this.output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getExceptionTrace() {
        return this.exceptionTrace;
    }

    public void setExceptionTrace(String exceptionTrace) {
        this.exceptionTrace = exceptionTrace;
    }

    public void addCommonAttribute(String name, Object value) {
        this.commonAttribute.put(name, value);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppPort() {
        return this.appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AuditInfo getAuditInfo() {
        return this.auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @JsonIgnore
    public Date getStartTimeAsDate() {
        if (getStartTime() != null){
            return new Date(getStartTime().longValue());
        }
        return null;
    }

    public void onComplete() {
        setEndTime(Long.valueOf(System.currentTimeMillis()));
        setCostTime(Long.valueOf(getEndTime().longValue() - getStartTime().longValue()));
    }



    @Override
    public String toString() {
        return "AuditLogEventData [commonAttribute=" + this.commonAttribute + ", eventType=" + this.eventType + ", appCode=" + this.appCode + ", appPort=" + this.appPort + ", userName=" + this.userName + ", url=" + this.url + ", ip=" + this.ip + ", method=" + this.method + ", className=" + this.className + ", methodName=" + this.methodName + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", costTime=" + this.costTime + ", messageText=" + this.messageText + ", input=" + this.input + ", output=" + this.output + ", exceptionTrace=" + this.exceptionTrace + "]";
    }
}
