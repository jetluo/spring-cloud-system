package com.cloud.kjetboy.server.zuul.config;

import com.sgcc.uap.safe.paramreader.IParamReader;
import com.sgcc.uap.safe.util.proxy.PropertiesUtilsProxy;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * @ClassName SafeParamReader
 * @Description TODO
 * @Author jet
 * @Date 2021/11/4 16:48
 * @Version 1.0
 **/
public class SafeParamReader implements IParamReader {
    private Properties properties;

    public SafeParamReader(){

    }
    public SafeParamReader(String safeConfigFilePath,Properties properties){
        if (StringUtils.isNotBlank(safeConfigFilePath)){
            Properties res = (new PropertiesUtilsProxy(new String[]{ safeConfigFilePath})).getProperties();
            res.putAll(properties);
            this.properties = res;
        }else {
            this.properties = properties;
        }
    }

    public void setProperties(Properties properties) {
        this.properties = properties;

    }

    @Override
    public Properties getPropertiesRoot() {
        return this.properties;
    }
}
