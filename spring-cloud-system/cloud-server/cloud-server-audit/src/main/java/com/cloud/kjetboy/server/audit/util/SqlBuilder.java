package com.cloud.kjetboy.server.audit.util;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jet
 */
public class SqlBuilder {
    private StringBuilder sql = new StringBuilder();

    private List<Object> args = new ArrayList();

    public String getSql() {
        return this.sql.toString();
    }

    public List<Object> getArgs() {
        return this.args;
    }

    public Object[] getArgsAsArray() {
        return this.args.toArray();
    }

    public void addSqlFragment(String fragment, Object... args) {
        this.sql.append(fragment);
        addArgs(args);
    }

    public void addArgs(Object... args) {
        if (args != null && args.length > 0){
            this.args.addAll(Arrays.asList(args));}
    }

    public static SqlBuilder newInstance() {
        return new SqlBuilder();
    }

    public static SqlBuilder newInstance(String sqlFragment) {
        SqlBuilder builder = new SqlBuilder();
        builder.addSqlFragment(sqlFragment, new Object[0]);
        return builder;
    }

    public static String escapeUnderline(String val) {
        if (StringUtils.isNotBlank(val) && StringUtils.contains(val, "_")){
            return val.replaceAll("_", "\\\\_");}
        return val;
    }

    public static Object[] escapeUnderline(Object... args) {
        List<Object> list = new ArrayList();
        for (int i = 0; i < args.length; i++) {
            Object val = args[i];
            if (val instanceof String) {
                list.add(escapeUnderline((String)val));
            } else {
                list.add(val);
            }
        }
        return list.toArray();
    }
}