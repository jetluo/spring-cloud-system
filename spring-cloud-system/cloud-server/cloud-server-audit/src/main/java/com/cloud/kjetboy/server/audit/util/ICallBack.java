package com.cloud.kjetboy.server.audit.util;

import org.springframework.http.ResponseEntity;

/**
 * @author jet
 */
public interface ICallBack {
    void callback(ResponseEntity<String> paramResponseEntity);
}
