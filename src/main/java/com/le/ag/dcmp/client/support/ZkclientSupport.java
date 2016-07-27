/** 
 * @Project_Name dcmp 
 * @File_Name ZkClientServiceSupport.java 
 * @Date 2016年7月14日上午12:04:24 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.client.support;

import java.util.List;

import com.le.ag.dcmp.bean.ZKNodeBean;
import com.le.ag.dcmp.bean.ZkConfigBean;
import com.le.ag.dcmp.client.ZookeeperClient;
import com.le.ag.dcmp.listener.IZKListener;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function ZkClient实现
 * @Reason
 */
public class ZkclientSupport implements ZookeeperClient {

	@Override
	public void start(ZkConfigBean zkConfigBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create(String path, byte[] content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setData(String path, byte[] content) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getData(String parth) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getChildren(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZKNodeBean getState(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerListeners(List<IZKListener> listeners) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executor(IZKListener listener, String path) {
		// TODO Auto-generated method stub

	}

}
