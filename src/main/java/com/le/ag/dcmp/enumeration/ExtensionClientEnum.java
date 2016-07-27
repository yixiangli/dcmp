/** 
 * @Project_Name dcmp 
 * @File_Name ExtensionClientEnum.java 
 * @Date 2016年7月14日下午1:48:11 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.enumeration;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function zk客户端枚举
 * @Reason
 */
public enum ExtensionClientEnum {

	zookeeperClient("Curator","Zkclient");
	
	public String curator;
	public String zkclient;
	
	private ExtensionClientEnum(String curator,String zkclient){
		this.curator = curator;
		this.zkclient = zkclient;
	}
}
