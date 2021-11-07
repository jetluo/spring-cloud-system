package com.cloud.kjetboy.server.audit.entity;

import com.cloud.kjetboy.server.audit.util.SqlBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author jet
 */
public class AuditLog {
    private static final long serialVersionUID = -7308879004143424449L;

    private String id;

    private String type;

    private String categoryId;

    private String categoryName;

    private String appCode;

    private String appName;

    private String appNodeIp;

    private String userName;

    private String userIp;

    private String theadName;

    private String className;

    private String methodName;

    private String requestUrl;

    private Date startTime;

    private Date endTime;

    private Long costTime;

    private String input;

    private String output;

    private String messageText;

    private String exceptiontrace;

    private String eventLevel;

    private String operationId;

    private String successCode;

    private String groupType;

    private String operationName;

    private Long computeCostTime;

    private String analysisEnable;

    private String eventType;

    @JsonIgnore
    private int revision;

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getEventLevel() {
        return this.eventLevel;
    }

    public String getAnalysisEnable() {
        return this.analysisEnable;
    }

    public void setAnalysisEnable(String analysisEnable) {
        this.analysisEnable = analysisEnable;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getOperationId() {
        return this.operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getSuccessCode() {
        return this.successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = (type == null) ? null : type.trim();
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = (categoryId == null) ? null : categoryId.trim();
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = (appCode == null) ? null : appCode.trim();
    }

    public String getAppNodeIp() {
        return this.appNodeIp;
    }

    public void setAppNodeIp(String appNodeIp) {
        this.appNodeIp = (appNodeIp == null) ? null : appNodeIp.trim();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = (userName == null) ? null : userName.trim();
    }

    public String getUserIp() {
        return this.userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = (userIp == null) ? null : userIp.trim();
    }

    public String getTheadName() {
        return this.theadName;
    }

    public void setTheadName(String theadName) {
        this.theadName = (theadName == null) ? null : theadName.trim();
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = (className == null) ? null : className.trim();
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = (methodName == null) ? null : methodName.trim();
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = (requestUrl == null) ? null : requestUrl.trim();
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = (input == null) ? null : input.trim();
    }

    public String getOutput() {
        return this.output;
    }

    public void setOutput(String output) {
        this.output = (output == null) ? null : output.trim();
    }

    public String getExceptiontrace() {
        return this.exceptiontrace;
    }

    public void setExceptiontrace(String exceptiontrace) {
        this.exceptiontrace = (exceptiontrace == null) ? null : exceptiontrace.trim();
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getComputeCostTime() {
        return this.computeCostTime;
    }

    public void setComputeCostTime(Long computeCostTime) {
        this.computeCostTime = computeCostTime;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;}
        if (obj == null){
            return false;}
        if (getClass() != obj.getClass()){
            return false;}
        AuditLog other = (AuditLog)obj;
        if (this.id == null) {
            if (other.id != null){
                return false;}
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public boolean transGroupType() {
        if (StringUtils.isBlank(this.groupType)) {
            this.groupType = "%Y-%m";
            return false;
        }
        switch (this.groupType) {
            case "0":
                this.groupType = "%Y";
                return false;
            case "1":
                this.groupType = "%Y-%m";
                return false;
            case "2":
                this.groupType = "%Y-%m-%d";
                return false;
            case "3":
                this.groupType = "%H";
                return true;
        }
        this.groupType = "%Y-%m";
        return false;
    }

    public void toLikeQuery() {
        if (StringUtils.isNotBlank(this.categoryId)){
            this.categoryId += "%";
        }
        if (StringUtils.isNotBlank(this.categoryName)){
            this.categoryName = "%" + this.categoryName + "%";
        }
        if (StringUtils.isNotBlank(this.appCode)){
            this.appCode = "%" + this.appCode + "%";
        }
        if (StringUtils.isNotBlank(this.appNodeIp)){
            this.appNodeIp = "%" + this.appNodeIp + "%";
        }
        if (StringUtils.isNotBlank(this.userName)){
            this.userName = "%" + this.userName + "%";
        }
        if (StringUtils.isNotBlank(this.userIp)){
            this.userIp = "%" + this.userIp + "%";
        }
        if (StringUtils.isNotBlank(this.theadName)){
            this.theadName = "%" + this.theadName + "%";
        }
        if (StringUtils.isNotBlank(this.methodName)){
            this.methodName = "%" + this.methodName + "%";
        }
        if (StringUtils.isNotBlank(this.requestUrl)){
            this.requestUrl = "%" + this.requestUrl + "%";
        }
        if (StringUtils.isNotBlank(this.className)){
            this.className = "%" + this.className + "%";
        }
        if (StringUtils.isNotBlank(this.messageText)){
            this.messageText = "%" + this.messageText + "%";
        }
        if (StringUtils.isNotBlank(this.operationName)){
            this.operationName = "%" + this.operationName + "%";
        }
    }

    public void escapeUnderLine(){
        this.requestUrl = SqlBuilder.escapeUnderline(this.requestUrl);
        this.userName = SqlBuilder.escapeUnderline(this.userName);
        this.userIp = SqlBuilder.escapeUnderline(this.userIp);
        this.messageText = SqlBuilder.escapeUnderline(this.messageText);
    }

    public Object[] getParam(){
        List<Object> list = new ArrayList();
        list.add(this.id);
        list.add(this.type);
        list.add(this.categoryName);
        list.add(this.categoryId);
        list.add(this.appName);
        list.add(this.appCode);
        list.add(this.appNodeIp);
        list.add(this.userName);
        list.add(this.userIp);
        list.add(this.theadName);
        list.add(this.className);
        list.add(this.methodName);
        list.add(this.requestUrl);
        list.add(this.startTime);
        list.add(this.endTime);
        list.add(this.costTime);
        list.add(this.input);
        list.add(this.output);
        list.add(this.messageText);
        list.add(this.exceptiontrace);
        list.add(this.computeCostTime);
        list.add(this.eventLevel);
        list.add(this.operationId);
        list.add(this.successCode);
        list.add(this.operationName);
        list.add(this.eventType);
        return list.toArray();
    }
}