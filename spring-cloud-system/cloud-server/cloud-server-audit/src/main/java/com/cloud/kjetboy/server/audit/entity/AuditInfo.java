package com.cloud.kjetboy.server.audit.entity;

/**
 *
 * @author jet
 *
 */
public class AuditInfo {
    private String categoryCode;

    private String categoryName;

    private String parentName;

    private String eventLevel;

    private String eventType;

    private String optType;

    private boolean defaultAuditItem;

    private String message;

    private String messageTemplate;

    private String messageTemplateCode;

    private String analysis;

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEventLevel() {
        return this.eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getAnalysis() {
        return this.analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOptType() {
        return this.optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public boolean isDefaultAuditItem() {
        return this.defaultAuditItem;
    }

    public void setDefaultAuditItem(boolean defaultAuditItem) {
        this.defaultAuditItem = defaultAuditItem;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageTemplate() {
        return this.messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplateCode() {
        return this.messageTemplateCode;
    }

    public void setMessageTemplateCode(String messageTemplateCode) {
        this.messageTemplateCode = messageTemplateCode;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
