package com.le.ag.dcmp.config.spring;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.le.ag.dcmp.client.DefaultZookeeperClient;
import com.le.ag.dcmp.enumeration.ZKRootNodePathEnum;
/** 
 * @Project_Name dcmp 
 * @File_Name SpringConfigLoader.java 
 * @Date 2016年7月19日下午4:54:01 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
import com.le.ag.dcmp.utils.FileOperator;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月19日
 * @since JDK 1.7
 * @Function
 * @Reason
 */
public class SpringConfigLoader extends PropertyPlaceholderConfigurer{

	private static final Logger logger = LoggerFactory.getLogger(SpringConfigLoader.class);
	
	private InputStream in_nocode;
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
		try {
			logger.info("start load the properties from zookeeper!");
			String node = props.getProperty("zookeeper.spring.properties");
			DefaultZookeeperClient.getInstance().start();
			byte[] data = DefaultZookeeperClient.getInstance().getData(ZKRootNodePathEnum.ZKRootNodePathEnum.configuration_path+ "/" + node);
			in_nocode = new ByteArrayInputStream(data);
			props.load(in_nocode);			
			createProperties(data, props);
			logger.info("end load the propertis from zookeeper!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setPropsFromLocal(props);
			logger.error("init properties exception",e);
		} finally {
			DefaultZookeeperClient.getInstance().close();
			if(null == in_nocode){
				try {
					in_nocode.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("inputStream close error!");
				}
			}			
		}
		// 初始化加载zk固定配置
		super.processProperties(beanFactoryToProcess, props);
	}

	/**
	 * 生成本地配置文件 预防zk取不到数据时 从这个文件里取
	 * 
	 * @param data
	 */
	private void createProperties(byte[] data, Properties props) {
		try {
			logger.info("start save the propertis!");
			InputStream stream = new ByteArrayInputStream(data);
			String path = "";
			try {
				path = ""+ this.getClass().getResource("/properties/zookeeper.properties").toURI();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				logger.error("get local resource uri exception",e);
			}
			path = FileOperator.getRootPath(path);	
			path = path + props.getProperty("zookeeper.local.properties")+ ".properties";
			FileOperator.writerFile(stream, path);		
			logger.info("end save the propertis! the path is "+path);
		} catch (Exception e) {
			logger.error("save the propertis exception!",e);
		}
	}

	/**
	 * 从zookeeper取数据异常从本地properties里取数据
	 * 
	 * @param props
	 */
	public void setPropsFromLocal(Properties props) {
		try {
			logger.info("load the propertis from location!");
			String fileName = "/"+ props.getProperty("zookeeper.local.properties")+ ".properties";
			InputStream in = getClass().getResourceAsStream(fileName);
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("load local properties fail",e);
		}
	}
	
}
