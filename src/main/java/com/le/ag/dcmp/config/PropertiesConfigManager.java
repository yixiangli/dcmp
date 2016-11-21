/** 
 * @Project_Name dcmp 
 * @File_Name PropertiesConfigManager.java 
 * @Date 2016年7月15日下午2:20:34 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.dcmp.utils.StringUtils;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月15日
 * @since JDK 1.7
 * @Function properties配置管理
 * @Reason
 */
public class PropertiesConfigManager {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigManager.class);
	
	private Map<String, Properties> proMap = new ConcurrentHashMap<String, Properties>();
	
	private static PropertiesConfigManager propertiesConfig = new PropertiesConfigManager();
	
	private PropertiesConfigManager() {}

	public static PropertiesConfigManager getInstance() {
		return propertiesConfig;
	}

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月15日 下午3:18:59
	 * @descriptor
	 * @Function properties配置文件加载
	 * @param fileName
	 * @return
	 */
	private Properties loadProperties(String fileName) {
		Properties properties = null;
		InputStream in = null;
		try {		
			properties = new Properties();
			String fileRealPath = "/properties/" + fileName + ".properties";
			in = getClass().getResourceAsStream(fileRealPath);	
			properties.load(in);
			logger.info("Successfully load Properties:" + properties);
		} catch (Exception e) {
			logger.error("Error reading " + fileName + " in loadProps() " + e.getMessage());
			logger.error("Ensure the " + fileName + " file is readable and in your classpath.");
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	
	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月15日 下午3:30:52
	 * @descriptor
	 * @Function 设置properties
	 * @param stream
	 * @param path
	 */
	public void setProps(InputStream stream,String path) {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
		Properties properties = proMap.get(path);
		if(properties == null){		
			properties = new Properties();		
		}
		try {		
			properties.load(buffer);	
			proMap.put(path, properties);
		} catch (Exception e) {
			logger.info(" in loadProps() " + e.getMessage());
		} finally {	
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	
	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月15日 下午3:19:40
	 * @descriptor
	 * @Function 获取properties配置信息
	 * @param fileName
	 * @param key
	 * @return
	 */
	public String getProperty(String fileName, String key) {
		if (StringUtils.isBlank(fileName) & StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("key is null");
		}
		Properties properties = proMap.get(key);
		if (properties == null) {
			properties = this.loadProperties(fileName);
			if (properties != null) {
				proMap.put(key, properties);
			} else {
				return null;
			}
		}
		String value = properties.getProperty(key);
		return value;
	}
	
}
