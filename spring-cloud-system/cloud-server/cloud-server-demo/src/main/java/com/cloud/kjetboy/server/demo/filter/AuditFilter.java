package com.cloud.kjetboy.server.demo.filter;


import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jet
 */
public class AuditFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        System.out.println("过滤器："+requestUri);
        //组装数据
        //AuditLogEventData auditLogEventData = buildAuditLogEventData(request);
        try {
            filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
        } finally {
            // 存储数据
            System.out.println("过滤器："+response.getStatus());
        }
    }
}
