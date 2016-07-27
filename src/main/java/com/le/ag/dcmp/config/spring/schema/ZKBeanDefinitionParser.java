package com.le.ag.dcmp.config.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.le.ag.dcmp.bean.ZkClientBean;
import com.le.ag.dcmp.bean.ZkConfigBean;
import com.le.ag.dcmp.enumeration.ExtensionClientEnum;
import com.le.ag.dcmp.utils.Constant;

/**
 * 
 * @developer liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年7月14日
 * @since JDK 1.7
 * @Function ZKBean解析工具
 * @Reason
 */
public class ZKBeanDefinitionParser implements BeanDefinitionParser{

	private final Class<?> beanClass;
	
	ZKBeanDefinitionParser(Class<?> beanClass){
		this.beanClass = beanClass;
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO Auto-generated method stub
		return doParse(element,parserContext,beanClass);
	}
	
	public BeanDefinition doParse(Element element, ParserContext parserContext,Class<?> beanClass){
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);

		// 处理zk:config配置 
		if(ZkConfigBean.class.equals(beanClass)){
	        beanDefinition.setLazyInit(false);        
	        beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CONFIG_CONNECT,element.getAttribute(Constant.ZK_XML_CONFIG_CONNECT));      
	        beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CONFIG_BASE_SLEEP_TIME_MS,element.getAttribute(Constant.ZK_XML_CONFIG_BASE_SLEEP_TIME_MS));
	        beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CONFIG_MAX_RETRIES,element.getAttribute(Constant.ZK_XML_CONFIG_MAX_RETRIES));
	        // bean id
	        String id = element.getAttribute(Constant.ZK_XML_CONFIG_ID);
	        
        	String idName = Constant.ZK_XML_CONFIG_DEFAULT_ID_BEAN;
        	if(StringUtils.hasLength(id)){		
        		idName = id;
        	}       
	        parserContext.getRegistry().registerBeanDefinition(idName, beanDefinition);        
	        beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CONFIG_ID, idName);
		}
		
        //处理zk:client配置
        if(ZkClientBean.class.equals(beanClass)){      	
        	Object reference = null;
        	if(StringUtils.hasLength(element.getAttribute(Constant.ZK_XML_CLIENT_REF))){	
        		reference = new RuntimeBeanReference(element.getAttribute(Constant.ZK_XML_CLIENT_REF));
        	}else{
        		reference = new RuntimeBeanReference(Constant.ZK_XML_CONFIG_DEFAULT_ID_BEAN);
        	}  	
        	beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CONFIG_DEFAULT_ID_BEAN, reference);
        	String type = ExtensionClientEnum.zookeeperClient.curator;
        	
        	if(StringUtils.hasLength(element.getAttribute(Constant.ZK_XML_CLIENT_TYPE))){
        		type = element.getAttribute(Constant.ZK_XML_CLIENT_TYPE);
        	}		
        	beanDefinition.getPropertyValues().addPropertyValue(Constant.ZK_XML_CLIENT_TYPE, type);      	
        	String id = element.getAttribute(Constant.ZK_XML_CLIENT_ID);        	
        	String idName = Constant.ZK_XML_CLIENT_DEFAULT_ID_BEAN;
        	if(StringUtils.hasLength(id)){		
        		idName = id;
        	}
        	parserContext.getRegistry().registerBeanDefinition(idName, beanDefinition);
        }
		return beanDefinition;
	}

}
