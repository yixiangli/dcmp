/** 
 * @Project_Name dcmp 
 * @File_Name DefaultZookeeperClient.java 
 * @Date 2016年7月25日下午9:06:01 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.le.ag.dcmp.enumeration.DefaultZKConfigEnum;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月25日
 * @since JDK 1.7
 * @Function 默认zookeeper client连接配置类
 * @Reason 均按照DefaultZKConfigEnum默认配置
 */
public class DefaultZookeeperClient {

	private static DefaultZookeeperClient defaultZookeeperClient = new DefaultZookeeperClient();

	private DefaultZookeeperClient(){}
	
    public static DefaultZookeeperClient getInstance(){ 	
    	return defaultZookeeperClient;
    }
    
	private CuratorFramework zkClient;
	 	
	public void start(){	
		zkClient = CuratorFrameworkFactory.newClient(DefaultZKConfigEnum.DefaultZKConfigEnum.connection, new ExponentialBackoffRetry(DefaultZKConfigEnum.DefaultZKConfigEnum.baseSleepTimeMs, DefaultZKConfigEnum.DefaultZKConfigEnum.maxRetries));
		zkClient.start();
	}

    public CuratorFramework getClient(){ 	
    	return zkClient;
    }
    
    public byte[] getData(String path) throws Exception{   	
    	byte[] data = zkClient.getData().forPath(path);  	
    	return data;
    }
    
	public void close(){	
		if(zkClient!=null){	
			zkClient.close();	
		}
	}
}
