package com.cloud.kjetboy.server.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class HttpRequestUtil {
    public static final String LOCALHOST = "localhost";

    public static final String LOCAL_127 = "127.0.0.1";

    public static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";

    /**
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String xff = getHeaderValue(request, "X-Forwarded-For");
        if (!StringUtils.isEmpty(xff)) {
            String[] array = xff.split(",");
            return array[0].trim();
        }
        return request.getRemoteAddr();
    }

    /**
     * @param location
     * @return
     */
    public static boolean isLocalhostAddr(String location) {
        return LOCALHOST.equals(location) || LOCAL_127.equals(location) || LOCAL_IPV6.equals(location);
    }

    public static HttpServletRequest getHttpServletRequestFromThreadLocal() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getHeaderValue(HttpServletRequest request, String key, String defaultValue) {
        String value = getHeaderValue(request, key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static String getHeaderValue(HttpServletRequest httpRequest, String key) {
        String value = httpRequest.getHeader(key);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            value = value.replaceAll(RegexContants.CRLF, "");
        }
        return value;
    }

    @SuppressWarnings("deprecation")
    public static String getParameterValue(HttpServletRequest request, String key) {
        return StringEscapeUtils.escapeHtml4(WebUtils.findParameterValue(request, key));
    }

    public static String getParameterValue(HttpServletRequest request, String key, String defaultValue) {
        String value = getParameterValue(request, key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static String getRequestURL(HttpServletRequest request) {
        return request.getRequestURI()
                + (request.getQueryString() != null ? "?"
                + request.getQueryString() : "");
    }

    public static String getContentType(HttpServletRequest request) {
        return request.getContentType();
    }

    public static String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    public static void setContentType(HttpServletResponse response, String contentType) {
        response.setContentType(contentType);
    }

    /**
     * 获取本机的IP地址
     *
     * @return
     * @throws IOException
     */
    public static String getLocalHostAddress() throws IOException {
        InetAddress candidateAddress = null;
        // 遍历所有的网络接口
        for (Enumeration<?> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
            // 在所有的接口下再遍历IP
            for (Enumeration<?> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                    if (inetAddr.isSiteLocalAddress()) {
                        // 如果是site-local地址，就是它了
                        return inetAddr.getHostAddress();
                    } else if (candidateAddress == null) {
                        // site-local类型的地址未被发现，先记录候选地址
                        candidateAddress = inetAddr;
                    }
                }
            }
        }
        if (candidateAddress != null) {
            return candidateAddress.getHostAddress();
        }
        // 如果没有发现 non-loopback地址.只能用最次选的方案
        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
        return jdkSuppliedAddress.getHostAddress();
    }
}
