/** 
 * @Project_Name dcmp 
 * @File_Name CuratorServiceSupport.java 
 * @Date 2016年7月14日上午12:03:28 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.client.support;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.dcmp.bean.ZKNodeBean;
import com.le.ag.dcmp.bean.ZkConfigBean;
import com.le.ag.dcmp.client.ZookeeperClient;
import com.le.ag.dcmp.enumeration.ZKRootNodePathEnum;
import com.le.ag.dcmp.listener.IZKListener;
import com.le.ag.dcmp.utils.StringUtils;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function Curator实现
 * @Reason
 */
public class CuratorSupport implements ZookeeperClient {
	
	private static final Logger logger = LoggerFactory.getLogger(CuratorSupport.class);

	private CuratorFramework curator;
	
	@Override
	public void start(ZkConfigBean zkConfigBean) {
		// TODO Auto-generated method stub
		curator = CuratorFrameworkFactory.newClient(zkConfigBean.getConnect(), new ExponentialBackoffRetry(zkConfigBean.getBaseSleepTimeMs(), zkConfigBean.getMaxRetries()));
		curator.start();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub	
		if(curator!=null){	
			curator.close();	
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		if(null == curator){ 		
    		return false;
    	}  	
    	return curator.isStarted();	
	}

	@Override
	public void create(String path, byte[] content) {
		// TODO Auto-generated method stub
		try {		
			curator.create().forPath(path, new byte[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Curator create node {} error:",path,e);
		}
	}

	@Override
	public void delete(String path) {
		// TODO Auto-generated method stub
		if(null != curator){
			curator.delete();
		}
	}

	@Override
	public void setData(String path, byte[] content) {
		// TODO Auto-generated method stub
		try {	
			curator.setData().forPath(path, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Curator setData for node {} error:",path,e);
		}
	}

	@Override
	public byte[] getData(String path) throws Exception {
		// TODO Auto-generated method stub
    	return curator.getData().forPath(path);
	}

	@Override
	public boolean checkExists(String path) {
		// TODO Auto-generated method stub
		try {		
			return curator.checkExists().forPath(path) == null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Curator check node {} error:",path,e);
		}
		return false;
	}

	@Override
	public List<String> getChildren(String path) {
		// TODO Auto-generated method stub
		try {		
			return curator.getChildren().forPath(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Curator get children node {} error:",path,e);
		}	
		return null;
	}

	@Override
	public ZKNodeBean getState(String path) {
		// TODO Auto-generated method stub
		ZKNodeBean zkNodeBean = new ZKNodeBean();
		try {			
			Stat stat = curator.checkExists().forPath(path);			
			zkNodeBean.setCtime(stat.getCtime());
			zkNodeBean.setMtime(stat.getMtime());
			zkNodeBean.setNumChildren(stat.getNumChildren());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Curator get node state {} error:",path,e);
		}		
		return zkNodeBean;
	}

	@Override
	public void registerListeners(final List<IZKListener> listeners) {
		// TODO Auto-generated method stub
		curator.getConnectionStateListenable().addListener(new ConnectionStateListener() {	     	
	           public void stateChanged(CuratorFramework client, ConnectionState newState) {             
	            	logger.info("CuratorFramework state changed:"+ newState);
	            	if(newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED){
	                    for(IZKListener listener : listeners){	                        	                    	
	                    	executor(listener,ZKRootNodePathEnum.ZKRootNodePathEnum.configuration_path);                     
	                        logger.info("Listener "+listener.getClass().getName()+" executed!");
	                    }
	                }
	            }	            
	        });
			curator.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
	            @Override
	            public void unhandledError(String message, Throwable e) {	               
	            	logger.error("CuratorFramework unhandledError: "+ message);
	            }          
	        });
	}

	@Override
	public void executor(final IZKListener listener, String path) {
		// TODO Auto-generated method stub
		for(final String p : listener.getPath()){
			logger.info("listener the configuration node:"+p);
			//使用Curator的NodeCache来做ZNode的监听，不用我们自己实现重复监听
			@SuppressWarnings("resource")
			final NodeCache cache = new NodeCache(curator, path+"/"+p);             
			cache.getListenable().addListener(new NodeCacheListener() {        
            	public void nodeChanged() throws Exception {
            		byte[] data = cache.getCurrentData().getData();               
            		logger.info("the node data is:"+StringUtils.byteTOString(data));           
            		if (data != null) {                   
            			listener.setValue(p, data);        		                   
            		}
            	}
	        });
	        try {
	            cache.start(true);
	        } catch (Exception e) {
	        	logger.error("Start NodeCache error for path: {}, error info: {}", listener.getPath(), e.getMessage());
	        }
	        
		}	
	}

}
