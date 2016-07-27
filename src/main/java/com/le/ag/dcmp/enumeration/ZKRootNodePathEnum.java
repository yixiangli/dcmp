/** 
 * @Project_Name dcmp 
 * @File_Name ZKNodePathBean.java 
 * @Date 2016年7月25日下午8:31:11 
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
 * @date 2016年7月25日
 * @since JDK 1.7
 * @Function zk根节点配置
 * @Reason
 */
public enum ZKRootNodePathEnum {

	ZKRootNodePathEnum("/zk/cluster","/zk/configuration","/zk/leader","/zk/locks","/zk/contents");
	
	public String cluster_path;	
	public String configuration_path;	
	public String leader_path;	
	public String locks_path;
	public String contents_path;
	
	private ZKRootNodePathEnum(String cluster_path,String configuration_path,String leader_path,String locks_path,String contents_path) {
		// TODO Auto-generated constructor stub
		this.cluster_path = cluster_path;
		this.configuration_path = configuration_path;
		this.leader_path = leader_path;
		this.locks_path = locks_path;
		this.contents_path = contents_path;
	}
}
