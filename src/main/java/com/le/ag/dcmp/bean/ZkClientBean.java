/** 
 * @Project_Name dcmp 
 * @File_Name ZKClientBean.java 
 * @Date 2016年7月14日下午1:34:09 
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
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function zk:client基础配置
 * @Reason
 */
public class ZkClientBean {

	//客户端类型
    private String type;
    //Client依赖
    private ZkConfigBean zkConfigBean;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ZkConfigBean getZkConfigBean() {
		return zkConfigBean;
	}

	public void setZkConfigBean(ZkConfigBean zkConfigBean) {
		this.zkConfigBean = zkConfigBean;
	}

}
