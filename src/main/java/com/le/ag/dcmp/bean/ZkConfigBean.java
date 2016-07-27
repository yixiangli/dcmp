/** 
 * @Project_Name dcmp 
 * @File_Name ZKConfigBean.java 
 * @Date 2016年7月13日下午11:50:39 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.bean;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月13日
 * @since JDK 1.7
 * @Function zk:config基础配置
 * @Reason
 */
public class ZkConfigBean {

	private String id;
	//zk集群地址
	private String connect;
	//初始sleep时间 
	private  int baseSleepTimeMs;
	//最大重试次数
	private int maxRetries;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public int getBaseSleepTimeMs() {
		return baseSleepTimeMs;
	}
	public void setBaseSleepTimeMs(int baseSleepTimeMs) {
		this.baseSleepTimeMs = baseSleepTimeMs;
	}
	public int getMaxRetries() {
		return maxRetries;
	}
	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}
	
}
