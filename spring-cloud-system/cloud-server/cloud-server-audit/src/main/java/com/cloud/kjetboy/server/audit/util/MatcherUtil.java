package com.cloud.kjetboy.server.audit.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @author jet
 */
public class MatcherUtil {
    private static PathMatcher pathMatcher = (PathMatcher) new AntPathMatcher();

    public static boolean matcher(List<String> list, String str) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (String pattern : list) {
            if (pathMatcher.match(pattern, str)) {
                return true;
            }
        }
        return false;
    }
}
