package com.cloud.kjetboy.server.audit.service.impl;

import com.cloud.kjetboy.server.audit.entity.AuditInfo;
import com.cloud.kjetboy.server.audit.entity.AuditLog;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.mapper.AuditLogMapper;
import com.cloud.kjetboy.server.audit.service.AuditLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author jet
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogMapper auditLogMapper;

    @Override
    public void saveLog(AuditLogEventData data) {
        //转换

        AuditLog logs=new AuditLog();
        logs.setAppCode(data.getAppCode());
        logs.setAppName(data.getAppName());
        logs.setClassName(data.getClassName());
        logs.setMethodName(data.getMethodName());
        logs.setCostTime(data.getCostTime());
        logs.setUserName(data.getUserName());
        logs.setUserIp(data.getIp());
        logs.setExceptiontrace(data.getExceptionTrace());
        logs.setRequestUrl(cleanUrl(data.getUrl()));
        data.setUrl(logs.getRequestUrl());
        if (data.getStartTime() != null) {
            logs.setStartTime(new Date(data.getStartTime().longValue()));
        }
        if (data.getEndTime() != null){
            logs.setEndTime(new Date(data.getEndTime().longValue()));
        }

        if (data.getEventType() != null) {
            logs.setType(data.getEventType().toString());
        }
        AuditInfo info = data.getAuditInfo();
        if (info != null) {
            logs.setCategoryId(info.getCategoryCode());
            logs.setCategoryName(info.getCategoryName());
            logs.setEventLevel(info.getEventLevel());
            logs.setEventType(info.getEventType());
            logs.setAnalysisEnable(info.getAnalysis());
            logs.setOperationName(info.getOptType());
        }
        Map<String, Object> commonAttribute = data.getCommonAttribute();
        if (commonAttribute != null) {
            logs.setAppNodeIp((String)commonAttribute.get("serviceIp"));
            logs.setTheadName((String)commonAttribute.get("threadName"));
        }
        if (data.getStatus().intValue() >= 400) {
            logs.setSuccessCode("0");
        } else {
            logs.setSuccessCode("1");
        }
        if (data.getInput() != null ){
            logs.setInput(data.getInput().toString());
        }
        if (data.getOutput() != null ){
            logs.setOutput(data.getOutput().toString());
        }
        auditLogMapper.insert(logs);
    }
    private String cleanUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (StringUtils.contains(url, "?")) {
            return StringUtils.split(url, "?")[0];
        }
        return url;
    }

}
