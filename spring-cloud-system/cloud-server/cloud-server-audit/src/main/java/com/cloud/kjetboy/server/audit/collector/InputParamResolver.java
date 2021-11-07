package com.cloud.kjetboy.server.audit.collector;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jet
 */
public interface InputParamResolver {

    Map<String, Object> resolve(Method paramMethod, Object[] paramArrayOfObject);


}
