package com.llbt.app.common;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * APP配置信息读取类
 * 
 * @author 魏晓亮
 * @version 1.0
 * 
 */
public class AppConfig {

    private static Log logger = LogFactory.getLog(AppConfig.class);
    private static Properties properties = new Properties();

    private static final String DEFAULT_CONFIG_FILE = "application.properties";

    static {
        try {
            properties.load(AppConfig.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE));
        } catch (Exception ex) {
            logger.error("Can't read the properties file. " + ex.getMessage());
        }
    }

    /**
     * 获取指定Key的配置项的值.
     * 
     * @param key
     *            配置项键值.
     * @return 配置项的值
     */
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
