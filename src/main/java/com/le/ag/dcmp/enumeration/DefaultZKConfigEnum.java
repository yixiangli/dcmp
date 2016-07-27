/** 
 * @Project_Name dcmp 
 * @File_Name DefaultZKConfigEnum.java 
 * @Date 2016年7月25日下午8:52:29 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.enumeration;

import com.le.ag.dcmp.config.PropertiesConfigManager;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月25日
 * @since JDK 1.7
 * @Function zk默认配置 用于保证无配置的zk连接
 * @Reason
 */
public enum DefaultZKConfigEnum {

	DefaultZKConfigEnum(1000,3);
	
	public  int baseSleepTimeMs;
	public int maxRetries;
	public String connection;
	
	private DefaultZKConfigEnum(int baseSleepTimeMs,int maxRetries){
		
		this.baseSleepTimeMs = baseSleepTimeMs;
		this.maxRetries = maxRetries;	
		connection = PropertiesConfigManager.getInstance().getProperty("zookeeper", "zookeeper.address");
	}
}
