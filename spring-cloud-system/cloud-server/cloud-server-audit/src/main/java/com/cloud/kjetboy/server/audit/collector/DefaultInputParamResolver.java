package com.cloud.kjetboy.server.audit.collector;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jet
 */
public class DefaultInputParamResolver implements InputParamResolver {
    private static final List<Class<?>> extendBodyClasses = Arrays.asList(new Class[] { HttpServletRequest.class, HttpServletResponse.class, MultipartFile.class, ServerHttpRequest.class, ServerHttpResponse.class, WebRequest.class, BindingResult.class, Model.class });

    @Override
    public Map<String, Object> resolve(Method method, Object[] args) {
        List<Object> list = (List<Object>) Stream.<Object>of(args).filter(arg -> !isExtendBodyClass(arg)).collect(Collectors.toList());
        return toMap(list);
    }
    protected boolean isExtendBodyClass(Object arg) {
        return extendBodyClasses.stream().anyMatch(cls -> cls.isInstance(arg));
    }
    protected Map<String, Object> toMap(List<Object> list) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put("param" + i, list.get(i));
        }
        return map;
    }

}
