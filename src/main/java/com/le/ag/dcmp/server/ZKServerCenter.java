/** 
 * @Project_Name dcmp 
 * @File_Name ZKServerCenter.java 
 * @Date 2016年7月14日下午3:29:41 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.server;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.le.ag.dcmp.bean.ZkClientBean;
import com.le.ag.dcmp.client.ZookeeperClient;
import com.le.ag.dcmp.client.ZookeeperClientProxy;
import com.le.ag.dcmp.enumeration.ExtensionClientEnum;
import com.le.ag.dcmp.utils.StringUtils;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function zk服务中心
 * @Reason
 */
public class ZKServerCenter implements InitializingBean, DisposableBean {

	private final static String prefx = "com.le.ag.dcmp.client.support.";
	private final static String className = "Support";
    private ZookeeperClient zookeeperClient;
    private ZkClientBean zkClientBean;

    /**
     * 根据xml文件配置 通过动态代理加载相应的zk客户端 默认走Curator
     */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		String client = prefx + zkClientBean.getType() + className; 
		if(StringUtils.isBlank(zkClientBean.getType())){	
			client = prefx + ExtensionClientEnum.zookeeperClient.curator + className; 
		}
		Class<?> c = Class.forName(client); 	
		ZookeeperClientProxy proxy = new ZookeeperClientProxy();
		zookeeperClient = (ZookeeperClient) proxy.bind(c);  
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		zookeeperClient.close();
	}
	
	public ZookeeperClient getZookeeper(){
		return this.zookeeperClient;
	}
	
	public void start(){
		zookeeperClient.start(zkClientBean.getZkConfigBean());
	}
	
	public boolean isStarted(){		
		return zookeeperClient.isStarted();
	}

	public ZkClientBean getZkClientBean() {
		return zkClientBean;
	}

	public void setZkClientBean(ZkClientBean zkClientBean) {
		this.zkClientBean = zkClientBean;
	}

}
