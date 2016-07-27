/** 
 * @Project_Name dcmp 
 * @File_Name ZookeeperServiceProxy.java 
 * @Date 2016年7月14日上午12:06:37 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function zookeeper服务动态代理类
 * @Reason 实现zk不同客户端的动态绑定
 */
public class ZookeeperClientProxy implements InvocationHandler{

	private Object target;
	
	public Object bind(Class<?> c) throws InstantiationException, IllegalAccessException {  	
		this.target = c.newInstance(); 
	    //取得代理对象  
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);   
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = method.invoke(target, args);  
	    return result;
	}

}
