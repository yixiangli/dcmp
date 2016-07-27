/** 
 * @Project_Name dcmp 
 * @File_Name ClientBeanLoader.java 
 * @Date 2016年7月26日下午2:01:13 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.config.spring;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.le.ag.dcmp.listener.IZKListener;
import com.le.ag.dcmp.server.ZKServerCenter;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月26日
 * @since JDK 1.7
 * @Function 
 * @Reason
 */
public class ClientBeanLoader implements InitializingBean {
 
	private ZKServerCenter zkServerCenter;
	            	
	private List<IZKListener> listeners;
	
	//设置Zookeeper启动后需要调用的监听或者，或者需要做的初始化工作。
	public void setListeners(List<IZKListener> listeners) {
	    this.listeners = listeners;
	 }
	
	//创建ZK连接
	public void afterPropertiesSet(){		
		if(!zkServerCenter.isStarted()){			
			zkServerCenter.start();
		}		
		zkServerCenter.getZookeeper().registerListeners(listeners);
	}

	public ZKServerCenter getZkServerCenter() {
		return zkServerCenter;
	}

	public void setZkServerCenter(ZKServerCenter zkServerCenter) {
		this.zkServerCenter = zkServerCenter;
	}

}
