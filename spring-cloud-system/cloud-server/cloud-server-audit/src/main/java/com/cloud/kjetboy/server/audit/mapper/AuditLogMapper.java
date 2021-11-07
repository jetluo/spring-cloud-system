package com.cloud.kjetboy.server.audit.mapper;

import com.cloud.kjetboy.server.audit.entity.AuditLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jet
 */
@Mapper
public interface AuditLogMapper {
    int insert(AuditLog paramAuditLog);
}
