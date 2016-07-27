/** 
 * @Project_Name dcmp 
 * @File_Name ZKNameSpaceHandler.java 
 * @Date 2016年7月14日上午12:51:16 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.le.ag.dcmp.bean.ZkClientBean;
import com.le.ag.dcmp.bean.ZkConfigBean;
import com.le.ag.dcmp.utils.Constant;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function 基于Spring可扩展Schema提供自定义配置支持
 * @Reason
 */
public class ZKNamespaceHandlerSupport extends NamespaceHandlerSupport{

	@Override
	public void init() {
		// TODO Auto-generated method stub	
    	registerBeanDefinitionParser(Constant.ZK_XML_CONFIG, new ZKBeanDefinitionParser(ZkConfigBean.class));  	
    	registerBeanDefinitionParser(Constant.ZK_XML_CLIENT, new ZKBeanDefinitionParser(ZkClientBean.class)); 
	}

}
